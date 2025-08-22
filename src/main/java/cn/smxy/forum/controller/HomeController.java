package cn.smxy.forum.controller;

import cn.smxy.forum.domain.other.TableDataInfo;
import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.param.query.HomePostPageListDTO;
import cn.smxy.forum.domain.vo.PostListVo;
import cn.smxy.forum.service.IPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Api(tags = "首页")
public class HomeController extends BaseController {

    @Autowired
    private IPostService postService;

    @GetMapping()
    @ApiOperation("首页查询")
    public TableDataInfo<PostListVo> getHomePostList(@Validated PageQuery pageQuery, HomePostPageListDTO postListDTO){
        startPage();
        return getDataTable(postService.getHomePostList(getUserId(), postListDTO));
    }

}
