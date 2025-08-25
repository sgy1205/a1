package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.CodeImage;
import cn.smxy.forum.mapper.CodeImageMapper;
import cn.smxy.forum.service.ICodeImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CodeImageServiceImpl extends ServiceImpl<CodeImageMapper, CodeImage> implements ICodeImageService {
}
