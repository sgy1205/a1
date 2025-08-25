package cn.smxy.forum.mapping;

import cn.smxy.forum.domain.entity.Points;
import cn.smxy.forum.domain.vo.PointListVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PointsMapping {

    PointsMapping INSTANCE= Mappers.getMapper(PointsMapping.class);


    PointListVo toListVo(Points points);

    List<PointListVo> toListVoList(List<Points> pointsList);

}
