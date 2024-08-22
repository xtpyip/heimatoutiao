package com.heima.admin.gateway.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.heima.admin.gateway.util.AppJwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AdminFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //拿到request和response对象、
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获取请求路径
        String path = request.getPath().toString();
        //如果是登录请求，直接放行
        if (path.contains("login")){
            return chain.filter(exchange);
        }
        //4. 不是登录相关，就需要校验：token
        //4.1 从请求头中获取token
        String token = request.getHeaders().getFirst("token");
        /**
         * 4.2 无效： 响应一个状态码：401
         *      有效：
         *          a. 不能为空
         *          b. 不能是伪造的，不能是过期的!
         */
        //不能为空
        if (StringUtils.isBlank(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //不能是伪造的
        Claims claimsBody = AppJwtUtil.getClaimsBody(token);
        if (claimsBody == null){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }else {
            //解析出用户Id,存在请求头中
            Object userId = claimsBody.get("id");
            //在header中添加新的信息
            ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
                httpHeaders.add("userId", userId + "");
            }).build();
            //重置header
            exchange.mutate().request(serverHttpRequest).build();
        }
        //不能是过期的!
        int res = AppJwtUtil.verifyToken(claimsBody);
        if (res == 1 || res == 2){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //4.3 token有效： 放行
        //放行
        return chain.filter(exchange);
    }
}