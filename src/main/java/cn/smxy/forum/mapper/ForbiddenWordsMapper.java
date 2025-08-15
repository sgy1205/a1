package cn.smxy.forum.mapper;

import cn.smxy.forum.domain.entity.ForbiddenWords;
import cn.smxy.forum.domain.param.query.ForbiddenWordsPageListDTO;
import cn.smxy.forum.domain.vo.ForbiddenWordsPageListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ForbiddenWordsMapper extends BaseMapper<ForbiddenWords> {

    /**
     * 分页查询违禁词列表信息
     */
    List<ForbiddenWordsPageListVo> selectForbiddenWordList(ForbiddenWordsPageListDTO forbiddenWordsPageListDTO);

}
