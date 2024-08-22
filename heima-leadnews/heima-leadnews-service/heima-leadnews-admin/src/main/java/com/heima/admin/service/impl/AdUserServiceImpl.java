package com.heima.admin.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdUserMapper;
import com.heima.admin.service.AdUserService;
import com.heima.common.exception.CustomException;
import com.heima.model.admin.dtos.AdLoginDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {
    /**
     * 管理端登录
     *
     * @param adLoginDto
     * @return {@link RequestMapping}
     */
    @Override
    public ResponseResult login(AdLoginDto adLoginDto) {
        //校验
        if (StringUtils.isBlank(adLoginDto.getName()) || StringUtils.isBlank(adLoginDto.getPassword())){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID);
        }
        //获取用户对象
        AdUser adUser = getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, adLoginDto.getName()));
        if (adUser == null){
            throw new CustomException(AppHttpCodeEnum.DATA_EXIST);
        }
        String salt = adUser.getSalt();
        String password = adLoginDto.getPassword();
        String pwd = DigestUtils.md5DigestAsHex(( password+salt ).getBytes());
        if (pwd.equals(adUser.getPassword())){
            Map<String,Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(adUser.getId().longValue()));
            adUser.setSalt("");
            adUser.setPassword("");
            map.put("user",adUser);
            return ResponseResult.okResult(map);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
    }
}