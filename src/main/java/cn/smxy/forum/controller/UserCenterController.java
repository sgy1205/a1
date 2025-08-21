package cn.smxy.forum.controller;

import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.param.update.UpdateUserCenterDetailDTO;
import cn.smxy.forum.domain.param.update.UpdateUserEmailDTO;
import cn.smxy.forum.domain.vo.UserCenterListVo;
import cn.smxy.forum.domain.vo.PostListVo;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.service.IUserService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userCenter")
@Api(tags = "用户中心")
public class UserCenterController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;

    @GetMapping("/userDetail/{userId}")
    @ApiOperation("获取个人中心用户详情数据")
    public R<UserCenterListVo> getUserCenterDetail(@PathVariable("userId") Long userId){
        return R.ok(userService.getUserCenterDetail(userId));
    }

    @PostMapping("/updateUserDetail")
    @ApiOperation("修改用户详情")
    public R updateUserCenterDetail(@RequestBody UpdateUserCenterDetailDTO updateUserCenterDetailDTO){
        return R.to(userService.updateUserCenterDetail(getUserId(),updateUserCenterDetailDTO),"修改");
    }

    @PostMapping("/updateEmail")
    @ApiOperation("修改用户邮箱")
    public R updateUserEmail(@Validated UpdateUserEmailDTO updateUserEmailDTO){
        return userService.updateUserEmail(getUserId(),updateUserEmailDTO.getEmail());
    }

    @PostMapping("/updateUserBackground")
    @ApiOperation("修改用户背景")
    public R updateUserBackground(@Validated @RequestParam(required = true) String background){
        return userService.updateUserBackground(getUserId(),background);
    }

    @GetMapping("/userCenterPost")
    @ApiOperation("获取用户帖子列表")
    public R<List<PostListVo>> getUserCenterPostList(@Validated PageQuery pageQuery, Long userId){
        startPage();
        return R.ok(postService.getUserCenterPostListVo(userId));
    }
}
