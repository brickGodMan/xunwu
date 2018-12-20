package com.qcy.service;

import com.qcy.entity.User;

/**
 * @ClassName IUserService
 * @Description 用户
 * @Author qiancy
 * @Date 2018/11/11 15:34
 * @Version 1.0
 **/
public interface IUserService {

    User findUserByName(String userName);

    /**
     * 根据电话号码寻找用户
     * @param telephone
     * @return
     */
    User findUserByTelephone(String telephone);

    /**
     * 通过手机号注册用户
     * @param telephone
     * @return
     */
    User addUserByPhone(String telephone);

}
