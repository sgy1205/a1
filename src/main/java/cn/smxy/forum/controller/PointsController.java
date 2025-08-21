package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Points;
import cn.smxy.forum.domain.other.TableDataInfo;
import cn.smxy.forum.domain.param.other.PageQuery;
import cn.smxy.forum.domain.vo.PointListVo;
import cn.smxy.forum.domain.vo.UserPointsRankVo;
import cn.smxy.forum.mapping.PointsMapping;
import cn.smxy.forum.service.IPointsService;
import cn.smxy.forum.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/point")
@Api(tags = "积分模块")
public class PointsController extends BaseController {

    @Autowired
    private IPointsService pointsService;

    @ApiOperation("获取当前用户积分记录列表")
    @GetMapping("/list")
    public TableDataInfo<List<PointListVo>> getPointList(@Validated PageQuery pageQuery) {
        Page<Points> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        LambdaQueryWrapper<Points> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Points::getUserId,getUserId());
        Page<Points> pointsPage = pointsService.page(page, lqw);

        List<PointListVo> pointListVos= PointsMapping.INSTANCE.toListVoList(pointsPage.getRecords());

        return getDataTable(pointListVos);
    }

    @GetMapping("/getUserPointsRank")
    @ApiOperation("获取用户积分排行列表")
    public TableDataInfo<List<UserPointsRankVo>> getUserPointsRank(@Validated PageQuery pageQuery) {
        startPage();
        List<UserPointsRankVo> userPointsRankVos =pointsService.getUserPointsRank();

        return getDataTable(userPointsRankVos);
    }

}
