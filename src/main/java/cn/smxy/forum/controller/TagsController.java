package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Tags;
import cn.smxy.forum.domain.vo.ForbiddenWordCheckResultVo;
import cn.smxy.forum.domain.vo.TagsListVo;
import cn.smxy.forum.mapping.TagsMapping;
import cn.smxy.forum.service.IForbiddenWordsCheckService;
import cn.smxy.forum.service.ITagsService;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Api(tags = "标签模块")
public class TagsController {

    @Autowired
    private ITagsService tagsService;
    @Autowired
    private IForbiddenWordsCheckService forbiddenWordsCheckService;

    @GetMapping("/select")
    @ApiOperation("查询标签列表")
    public R<List<TagsListVo>> selectTagsList(String tagsName){
        LambdaQueryWrapper<Tags> lqw = new LambdaQueryWrapper<>();
        lqw.like(Tags::getTagsName, tagsName);
        List<Tags> list = tagsService.list(lqw);

        return R.ok(TagsMapping.INSTANCE.toTagsListVos(list));
    }

    @PostMapping()
    @ApiOperation("添加标签")
    public R addTags(String tagsName){
        LambdaQueryWrapper<Tags> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Tags::getTagsName, tagsName);
        if (tagsService.count(lqw) > 0) {
            return R.fail("该标签已存在");
        }else{
            Tags tags = new Tags();
            tags.setTagsName(tagsName);
            ForbiddenWordCheckResultVo forbiddenWordCheckResultVo = forbiddenWordsCheckService.checkForbiddenWords(tagsName);
            if(forbiddenWordCheckResultVo.isResult()){
                return R.to(tagsService.save(tags),"添加");
            }else{
                return R.fail("该标签存在违禁词："+forbiddenWordCheckResultVo.getForbiddenWords());
            }
        }
    }

    @GetMapping()
    @ApiOperation("获取推荐的标签")
    public R<List<TagsListVo>> getRecommendTagsList(){
        return R.ok(TagsMapping.INSTANCE.toTagsListVos(tagsService.getRecommendTagsList()));
    }

}
