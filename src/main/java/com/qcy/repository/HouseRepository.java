package com.qcy.repository;

import com.qcy.entity.House;
import org.springframework.data.repository.CrudRepository;

/**
 * @ClassName HouseRepository
 * @Description TODO
 * @Author qiancy
 * @Date 2018/12/19 16:36
 * @Version 1.0
 **/
public interface HouseRepository extends CrudRepository<House,Long> {
}
