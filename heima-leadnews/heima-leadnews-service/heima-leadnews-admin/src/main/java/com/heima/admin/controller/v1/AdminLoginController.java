package com.heima.admin.controller.v1;

import com.heima.admin.service.AdUserService;
import com.heima.model.admin.dtos.AdLoginDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//         - id: user
//          uri: lb://leadnews-user
//          predicates:
//            - Path=/user/**

//  http://localhost:8801/app/user/api/v1/login/login_auth/
// ~/app/(.*)
// /api/v1/login/login_auth

// http://localhost:8803/service_6001/admin/login/in
//
// login/in
@RestController
@RequestMapping("/login")
public class AdminLoginController {
    @Autowired
    private AdUserService adUserService;

    /**
     * 管理端登录
     *
     * @param adLoginDto
     * @return {@link RequestMapping}
     */
    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdLoginDto adLoginDto){
        System.out.println(adLoginDto);
        return adUserService.login(adLoginDto);
    }
}