package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.Points;
import cn.smxy.forum.domain.vo.PointListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PointsMapping {

    PointsMapping INSTANCE= Mappers.getMapper(PointsMapping.class);

    PointListVo toListVo(Points points);

    List<PointListVo> toListVoList(List<Points> pointsList);

}
