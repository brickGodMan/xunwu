package com.qcy.repository;

import com.qcy.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @ClassName RoleRepository
 * @Description TODO
 * @Author qiancy
 * @Date 2018/11/11 15:36
 * @Version 1.0
 **/
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findRolesByUserId(Long userId);
}
