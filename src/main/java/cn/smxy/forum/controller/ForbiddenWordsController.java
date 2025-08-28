package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.ForbiddenWords;
import cn.smxy.forum.domain.other.TableDataInfo;
import cn.smxy.forum.domain.param.insert.AddForbiddenWordDTO;
import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.param.query.ForbiddenWordsPageListDTO;
import cn.smxy.forum.domain.param.update.UpdateForbiddenWordsDTO;
import cn.smxy.forum.domain.vo.ForbiddenWordsDetailVo;
import cn.smxy.forum.domain.vo.ForbiddenWordsPageListVo;
import cn.smxy.forum.mapping.ForbiddenWordsMapping;
import cn.smxy.forum.service.IForbiddenWordsService;
import cn.smxy.forum.utils.PageUtils;
import cn.smxy.forum.utils.R;
import cn.smxy.forum.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "违禁词模块")
@RestController
@RequestMapping("/forbiddenWord")
public class ForbiddenWordsController extends BaseController {

    @Autowired
    private IForbiddenWordsService forbiddenWordsService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("新增违禁词")
    @PostMapping
    @PreAuthorize("@myExpressionUtil.myAuthority('forbiddenWord:add')")
    public R createForbiddenWord(@RequestBody @Validated AddForbiddenWordDTO addForbiddenWordDTO){
        redisUtil.deleteObject("forbiddenWordList");
        redisUtil.deleteObject("forbiddenWordRegexList");
        return R.to(forbiddenWordsService.save(ForbiddenWordsMapping.INSTANCE.toCreate(addForbiddenWordDTO)),"添加");
    }

    @ApiOperation("查询违禁词详情")
    @GetMapping("/{forbiddenWordsId}")
    @PreAuthorize("@myExpressionUtil.myAuthority('forbiddenWord:get')")
    public R<ForbiddenWordsDetailVo> selectDtl(@PathVariable Long forbiddenWordsId){
        return R.ok(ForbiddenWordsMapping.INSTANCE.toDtl(forbiddenWordsService.getById(forbiddenWordsId)));
    }

    @ApiOperation("编辑违禁词信息")
    @PutMapping
    @PreAuthorize("@myExpressionUtil.myAuthority('forbiddenWord:put')")
    public R updateForbiddenWord(@RequestBody @Validated UpdateForbiddenWordsDTO updateForbiddenWordsDTO){
        redisUtil.deleteObject("forbiddenWordList");
        redisUtil.deleteObject("forbiddenWordRegexList");
        return R.to(forbiddenWordsService.updateById(ForbiddenWordsMapping.INSTANCE.toUpdate(updateForbiddenWordsDTO)),"修改");
    }

    @ApiOperation(value = "删除违禁词信息",notes = "支持批量删除")
    @DeleteMapping("{forbiddenWordIds}")
    @PreAuthorize("@myExpressionUtil.myAuthority('forbiddenWord:delete')")
    public R deleteForbiddenWord(@PathVariable List<Long> forbiddenWordIds){
        redisUtil.deleteObject("forbiddenWordList");
        redisUtil.deleteObject("forbiddenWordRegexList");
        return R.to(forbiddenWordsService.removeByIds(forbiddenWordIds),"删除");
    }

    @ApiOperation("分页查询违禁词列表信息")
    @GetMapping("/list")
    @PreAuthorize("@myExpressionUtil.myAuthority('forbiddenWord:list')")
    public TableDataInfo<ForbiddenWordsPageListVo> list(@Validated PageQuery pageQuery, @Validated ForbiddenWordsPageListDTO forbiddenWordsPageListDTO) {
        startPage();
        List<ForbiddenWordsPageListVo> forbiddenWords = forbiddenWordsService.selectForbiddenWordList(forbiddenWordsPageListDTO);

        return getDataTable(forbiddenWords);
    }
}
