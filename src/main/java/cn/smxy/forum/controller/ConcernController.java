package cn.smxy.forum.controller;

import cn.smxy.forum.domain.other.TableDataInfo;
import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.vo.ConcernListVo;
import cn.smxy.forum.domain.vo.FansListVo;
import cn.smxy.forum.service.IConcernService;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concern")
@Api(tags = "关注模块")
public class ConcernController extends BaseController {

    @Autowired
    private IConcernService concernService;

    @PutMapping("/{userId}")
    @ApiOperation("修改关注状态")
    public R updateConcern(@PathVariable("userId") Long userId) {
        return R.to(concernService.updateConcern(userId),"关注");
    }


    @GetMapping("/concernList")
    @ApiOperation("获取关注列表")
    public TableDataInfo<List<ConcernListVo>> getConcernList(@Validated PageQuery pageQuery, Long userId){
        startPage();
        return getDataTable(concernService.getConcernList(userId));
    }

    @GetMapping("/fansList")
    @ApiOperation("获取粉丝列表")
    public TableDataInfo<List<FansListVo>> getFansList(@Validated PageQuery pageQuery,Long userId){
        startPage();
        return getDataTable(concernService.getFansList(userId));
    }
}
