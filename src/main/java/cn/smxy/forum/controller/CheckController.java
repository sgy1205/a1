package cn.smxy.forum.controller;

import cn.smxy.forum.service.ICheckService;
import cn.smxy.forum.service.IPointsService;
import cn.smxy.forum.utils.AjaxResult;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckController extends BaseController {

    @Autowired
    private ICheckService checkService;
    @Autowired
    private IPointsService pointsService;

    @GetMapping("/checkstatus")
    @ApiOperation("获取用户今天是否签到 0-未签到 1-已签到")
    public AjaxResult getCheckStatus() {
        AjaxResult ajaxResult = AjaxResult.success();
        Long userId = getUserId();
        ajaxResult.put("checkStatus", checkService.getCheckStatus(userId)?1:0);
        ajaxResult.put("points", pointsService.getUserPoints(userId));
        return ajaxResult;
    }

    @PostMapping("/userCheck")
    @ApiOperation("签到")
    public R check(){
        Long userId = getUserId();
        return checkService.check(userId);
    }

    @GetMapping("/getcheckNum")
    @ApiOperation("获取连续签到天数")
    public AjaxResult getCheckNum(){
        AjaxResult ajaxResult = AjaxResult.success();
        Long userId = getUserId();
        ajaxResult.put("checkNum", checkService.getCheckNum(userId));
        return ajaxResult;
    }

}
