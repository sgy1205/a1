package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.param.insert.AddPostDTO;
import cn.smxy.forum.domain.param.update.UpdatePostDTO;
import cn.smxy.forum.domain.vo.PostUpdateDetailVo;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static cn.smxy.forum.constant.Constants.DELETE;

@RestController
@RequestMapping("/post")
@Api(tags = "帖子模块")
public class PostController extends BaseController {

    @Autowired
    private IPostService postService;

    @PostMapping()
    @ApiOperation("添加帖子")
    public R addPost(@RequestBody AddPostDTO addPostDTO) {
        return R.to(postService.addPost(getUserId(),addPostDTO),"添加");
    }

    @PutMapping()
    @ApiOperation("修改帖子")
    public R updatePost(@RequestBody UpdatePostDTO updatePostDTO) {
        Post post = postService.getById(updatePostDTO.getPostId());
        if(!post.getUserId().equals(getUserId())){
            return R.fail("这不是你的帖子，你无权修改");
        }
        return R.to(postService.updatePost(post,updatePostDTO),"修改");
    }

    @DeleteMapping("/{postId}")
    @ApiOperation("删除帖子")
    public R deletePost(@PathVariable("postId") Long postId){
        Post post = postService.getById(postId);
        post.setDelFlag(DELETE);
        return R.to(postService.updateById(post),"删除");
    }

    @GetMapping("/{postId}")
    @ApiOperation("获取修改帖子时的详情")
    public R<PostUpdateDetailVo> getPostDetail(@PathVariable("postId") Long postId){
        return R.ok(postService.getPostDetail(postId));
    }

}
