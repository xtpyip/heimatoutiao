package com.heima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AdLoginDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;

public interface AdUserService extends IService<AdUser> {
    /**
     * 管理端登录
     * @param adLoginDto
     * @return {@link RequestMapping}
     */
    ResponseResult login(AdLoginDto adLoginDto);
}