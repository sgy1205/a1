package cn.smxy.yeardesign.controller;

import cn.smxy.yeardesign.domain.entity.User;
import cn.smxy.yeardesign.service.IUserService;
import cn.smxy.yeardesign.utils.AjaxResult;
import cn.smxy.yeardesign.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private IUserService userService;


}
