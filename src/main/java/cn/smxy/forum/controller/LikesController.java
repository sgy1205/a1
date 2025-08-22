package cn.smxy.forum.controller;

import cn.smxy.forum.domain.param.update.UpdateLikesDTO;
import cn.smxy.forum.service.ILikesService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@Api(tags = "点赞模块")
public class LikesController extends BaseController{

    @Autowired
    private ILikesService likesService;

    @PostMapping()
    @ApiOperation("修改点赞状态")
    public R updateLikes(@RequestBody UpdateLikesDTO updateLikesDTO){
        return R.to(likesService.updateLikes(getUserId(),updateLikesDTO),"点赞");
    }

}
