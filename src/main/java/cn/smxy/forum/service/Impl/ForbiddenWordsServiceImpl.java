package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.ForbiddenWords;
import cn.smxy.forum.domain.param.query.ForbiddenWordsPageListDTO;
import cn.smxy.forum.domain.vo.ForbiddenWordsPageListVo;
import cn.smxy.forum.mapper.ForbiddenWordsMapper;
import cn.smxy.forum.service.IForbiddenWordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ForbiddenWordsServiceImpl extends ServiceImpl<ForbiddenWordsMapper, ForbiddenWords> implements IForbiddenWordsService {

    @Autowired
    private ForbiddenWordsMapper forbiddenWordsMapper;

    @Override
    public List<ForbiddenWordsPageListVo> selectForbiddenWordList(ForbiddenWordsPageListDTO forbiddenWordsPageListDTO) {
        return forbiddenWordsMapper.selectForbiddenWordList(forbiddenWordsPageListDTO);
    }
}
