package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Post;
import cn.smxy.forum.domain.entity.PostAudit;
import cn.smxy.forum.domain.other.TableDataInfo;
import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.param.query.PostManagerPageListDTO;
import cn.smxy.forum.domain.param.update.UpdatePostAuditStatusDTO;
import cn.smxy.forum.domain.vo.PostManagerPageListVo;
import cn.smxy.forum.service.IPostAuditService;
import cn.smxy.forum.service.IPostService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.smxy.forum.constant.Constants.*;

@RestController
@RequestMapping("/postManager")
@Api(tags = "帖子管理模块")
public class PostManagerController extends BaseController {

    @Autowired
    private IPostService postService;
    @Autowired
    private IPostAuditService postAuditService;

    @GetMapping("/pageList")
    @ApiOperation("帖子分页查询")
    public TableDataInfo<List<PostManagerPageListVo>> postManagerPagelist(@Validated PageQuery pageQuery, PostManagerPageListDTO postManagerPageListDTO) {
        startPage();
        List<PostManagerPageListVo> postManagerPageListVos = postService.getPostManagerPageListVo(postManagerPageListDTO);
        return getDataTable(postManagerPageListVos);
    }

    @PostMapping("/recommend/{postId}")
    @ApiOperation("推荐 0-未推荐 1-推荐")
    @Transactional(rollbackFor = Exception.class)
    public R postManagerRecommend(@PathVariable("postId") Long postId) {
        PostAudit audit = postAuditService.getById(postId);

        if(audit.getAuditStatus().equals("0") || audit.getAuditStatus().equals("2")){
            return R.fail("该帖子未通过审核无法推荐");
        }

        if(postService.postManagerRecommend(postId)){
            return R.ok();
        }else {
            return R.fail("修改审核状态失败");
        }
    }

    @DeleteMapping("/deletePost/{postId}")
    @ApiOperation("修改帖子删除状态")
    public R deletePost(@PathVariable("postId") Long postId) {
        Post post = postService.getById(postId);
        if(post.getDelFlag().equals(NO_DELETE)){
            post.setDelFlag(DELETE);
        }else if(post.getDelFlag().equals(DELETE)){
            post.setDelFlag(NO_DELETE);
            postService.addPostNotification("你的帖子："+post.getTitle()+"  已恢复",post.getUserId(),postId);
        }
        return postService.updateById(post)?R.ok():R.fail("修改删除状态失败");
    }

    @PostMapping("/audit")
    @ApiOperation("帖子审核")
    public R updatePostAuditStatus(@Validated @RequestBody UpdatePostAuditStatusDTO updatePostAuditStatusDTO) {
        return postAuditService.updatePostAuditStatus(updatePostAuditStatusDTO)>0?R.ok():R.fail("修改审核状态失败");
    }

}
