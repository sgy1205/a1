package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.ForbiddenWords;
import cn.smxy.forum.domain.param.insert.AddForbiddenWordDTO;
import cn.smxy.forum.domain.param.update.UpdateForbiddenWordsDTO;
import cn.smxy.forum.domain.vo.ForbiddenWordsDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ForbiddenWordsMapping {

    //获取当前接口的实例化代理对象
    ForbiddenWordsMapping INSTANCE = Mappers.getMapper(ForbiddenWordsMapping.class);

    //违禁词新增转实体
    ForbiddenWords toCreate(AddForbiddenWordDTO addForbiddenWordDTO);

    //违禁词详情
    ForbiddenWordsDetailVo toDtl(ForbiddenWords msForbiddenWords);

    //违禁词编辑转实体
    ForbiddenWords toUpdate(UpdateForbiddenWordsDTO updateForbiddenWordsDTO);


}
