package cn.smxy.forum.controller;

import cn.smxy.forum.domain.entity.Silence;
import cn.smxy.forum.domain.param.insert.AddSilenceDTO;
import cn.smxy.forum.domain.param.update.UpdateSilenceStatusDTO;
import cn.smxy.forum.service.ISilenceService;
import cn.smxy.forum.utils.AjaxResult;
import cn.smxy.forum.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/silence")
@Api(tags = "用户禁言模块")
public class SilenceController extends BaseController {

    @Autowired
    private ISilenceService silenceService;

    @GetMapping("/getSilenceStatus/{userId}")
    @ApiOperation("获取禁言状态（0-未禁言 1-暂时禁言 2-永久禁言）")
    public AjaxResult getSilenceStatus(@PathVariable("userId") Long userId){
        AjaxResult ajaxResult = AjaxResult.success();

        String userSilenceStatus = silenceService.getUserSilenceStatus(userId);
        ajaxResult.put("silenceStatus",userSilenceStatus);
        if(!userSilenceStatus.equals("0")){
            Silence silenceRecord = silenceService.getSilenceRecord(userId);
            ajaxResult.put("signatureReason",silenceRecord.getSignatureReason());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedTime = sdf.format(silenceRecord.getSignatureTime());
            ajaxResult.put("signatureTime", formattedTime);
        }

        return ajaxResult;
    }

    @PostMapping("/setSilence")
    @ApiOperation("禁言")
    @PreAuthorize("@myExpressionUtil.myAuthority('userManager:setSilence')")
    public R setSilence(@RequestBody @Validated AddSilenceDTO addSilenceDTO){
        return silenceService.addSilence(addSilenceDTO)?R.ok():R.fail("禁言失败");
    }

    @PostMapping("/updateSilence")
    @ApiOperation("修改禁言状态")
    @PreAuthorize("@myExpressionUtil.myAuthority('userManager:updateSilence')")
    public R updateSilenceStatus(@RequestBody @Validated UpdateSilenceStatusDTO updateSilenceStatusDTO){
        return silenceService.updateSilenceStatus(updateSilenceStatusDTO)?R.ok():R.fail("修改失败");
    }

}
