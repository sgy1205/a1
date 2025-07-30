package cn.smxy.yeardesign.controller;

import cn.smxy.yeardesign.domain.entity.User;
import cn.smxy.yeardesign.domain.param.other.LoginBodyDTO;
import cn.smxy.yeardesign.service.ILoginService;
import cn.smxy.yeardesign.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Api(tags = "登录模块")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public AjaxResult login(LoginBodyDTO loginBodyDTO) {
        AjaxResult ajaxResult = AjaxResult.success("登录成功");
        String token = loginService.login(loginBodyDTO);
        ajaxResult.put("token", token);
        return ajaxResult;
    }

}
