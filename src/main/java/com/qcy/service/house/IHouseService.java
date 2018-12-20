package com.qcy.service.house;

import com.qcy.service.ServiceResult;
import com.qcy.web.dto.HouseDTO;
import com.qcy.web.form.HouseForm;

/**
 * @ClassName IHouseService
 * @Description TODO
 * @Author qiancy
 * @Date 2018/12/19 16:57
 * @Version 1.0
 **/
public interface IHouseService {

    /**
     * 新增
     * @param houseForm
     * @return
     */
    ServiceResult<HouseDTO> save(HouseForm houseForm);
}
