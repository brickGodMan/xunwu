package com.qcy.service.house;

import com.qcy.service.ServiceResult;
import com.qcy.web.dto.HouseDTO;
import com.qcy.web.form.HouseForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName HouseServiceImpl
 * @Description TODO
 * @Author qiancy
 * @Date 2018/12/20 11:02
 * @Version 1.0
 **/
@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceResult<HouseDTO> save(HouseForm houseForm) {

        return null;
    }
}
