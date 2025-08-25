package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.CodeImage;
import cn.smxy.forum.domain.entity.LoginUser;
import cn.smxy.forum.domain.entity.User;
import cn.smxy.forum.domain.param.other.LoginBodyDTO;
import cn.smxy.forum.service.ICodeImageService;
import cn.smxy.forum.service.ILoginService;
import cn.smxy.forum.service.IUserService;
import cn.smxy.forum.utils.AjaxResult;
import cn.smxy.forum.utils.R;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping
@Api(tags = "登录模块")
public class LoginController {

    @Autowired
    private ILoginService loginService;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ICodeImageService codeImageService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public AjaxResult login(@RequestBody LoginBodyDTO loginBodyDTO) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName,loginBodyDTO.getLoginStr()).or().eq(User::getEmail,loginBodyDTO.getLoginStr());
        User user = userService.getOne(lqw);
        if (user == null) {
            return AjaxResult.error("账号或邮箱错误");
        }else{
            AjaxResult ajaxResult = AjaxResult.success("登录成功");
            String token = loginService.login(user.getUserName(), loginBodyDTO.getPassword());
            if (token.equals("密码错误")) {
                return AjaxResult.error("密码错误");
            }
            ajaxResult.put("token", token);
            ajaxResult.put("userId", user.getUserId());
            return ajaxResult;
        }
    }

    @GetMapping("/userInfo/{userId}")
    @ApiOperation("获取当前登录用户信息")
    public R<User> getUserInfo(@PathVariable("userId") Long userId) {
        LoginUser loginUser = redisUtil.getCacheObject("user:" + userId);
        return R.ok(loginUser.getUser());
    }

    @GetMapping("/getCaptchaImage")
    @ApiOperation("获取验证码图片")
    public R<CodeImage> getCaptchaImage() {
        Random random = new Random();

        int randomNumber = random.nextInt(4) + 1;

        CodeImage codeImage = codeImageService.getById(randomNumber);

        return R.ok(codeImage);
    }

}
