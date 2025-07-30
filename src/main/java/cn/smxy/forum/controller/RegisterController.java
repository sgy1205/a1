package cn.smxy.forum.controller;

import cn.smxy.forum.domain.param.insert.RegisterDTO;
import cn.smxy.forum.service.IRegisterService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Api(tags = "注册模块")
public class RegisterController {

    @Autowired
    private IRegisterService registerService;

    @PostMapping("/register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterDTO registerDTO) {
        return registerService.register(registerDTO)?R.ok():R.fail("注册失败");
    }
}
