package com.qcy.repository;

import com.qcy.entity.SubwayStation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @ClassName SubwayStationRepository
 * @Description TODO
 * @Author qiancy
 * @Date 2018/12/18 16:35
 * @Version 1.0
 **/
public interface SubwayStationRepository extends CrudRepository<SubwayStation,Long> {

    /**
     * 获取所有地铁站点
     * @param subwayId
     * @return
     */
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
