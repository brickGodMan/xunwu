package com.imooc.repository;

import com.imooc.entity.Subway;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @ClassName SubwayRepository
 * @Description 获取所有的地铁站点
 * @Author qiancy
 * @Date 2018/12/18 16:44
 * @Version 1.0
 **/
public interface SubwayRepository extends CrudRepository<Subway,Long> {
    /**
     * 根据城市名字获取所有的地铁站点
     * @param cityEnName
     * @return
     */
    List<Subway> findAllByCityEnName(String cityEnName);
}
