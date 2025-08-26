package cn.smxy.forum.service;

import cn.smxy.forum.domain.entity.Tags;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ITagsService extends IService<Tags> {

    List<Tags> getRecommendTagsList();

}
