package cn.smxy.forum.controller;

import cn.smxy.forum.domain.param.update.UpdatePasswordDTO;
import cn.smxy.forum.service.IUserService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    public R updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        return R.to(userService.updatePassword
                (getUserId(),updatePasswordDTO.getOldPassword(),updatePasswordDTO.getNewPassword()),"修改密码");
    }


}
