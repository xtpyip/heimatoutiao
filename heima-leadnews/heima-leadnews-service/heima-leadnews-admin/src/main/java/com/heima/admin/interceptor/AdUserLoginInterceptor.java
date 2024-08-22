package com.heima.admin.interceptor;

import com.heima.model.admin.pojos.AdUser;
import com.heima.utils.thread.AdUserThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdUserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");

        if (userId != null) {
            AdUser adUser = new AdUser();
            adUser.setId(Long.valueOf(userId));
            AdUserThreadLocalUtil.setUser(adUser);
        }
        return true;
    }

    //响应过滤方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //注意： 当线程使用完数据，一定要记得清空ThreadLocal，避免引发：OutOfMemery 内存溢出问题。
        AdUserThreadLocalUtil.clear();
    }
}