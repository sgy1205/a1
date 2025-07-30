package cn.smxy.forum.controller;

import cn.smxy.forum.domain.param.other.LoginBodyDTO;
import cn.smxy.forum.service.ILoginService;
import cn.smxy.forum.utils.AjaxResult;
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
