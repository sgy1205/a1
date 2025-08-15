package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.ForbiddenWords;
import cn.smxy.forum.domain.param.query.ForbiddenWordsPageListDTO;
import cn.smxy.forum.domain.vo.ForbiddenWordsPageListVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IForbiddenWordsService extends IService<ForbiddenWords> {

    /**
     * 分页查询违禁词列表信息
     */
    List<ForbiddenWordsPageListVo> selectForbiddenWordList(ForbiddenWordsPageListDTO forbiddenWordsPageListDTO);

}
