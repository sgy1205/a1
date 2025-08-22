package cn.smxy.forum.controller;

import cn.smxy.forum.service.ICollectionService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
@Api(tags = "收藏模块")
public class CollectionController extends BaseController {

    @Autowired
    private ICollectionService collectionService;

    @PostMapping("/{postId}")
    @ApiOperation("修改帖子收藏状态")
    public R collectionPost(@PathVariable("postId") Long postId) {
        return R.to(collectionService.collectionPost(postId,getUserId()),"收藏");
    }

}
