package com.qcy.entity;

import com.qcy.ApplicationTests;
import com.qcy.repository.RoleRepository;
import com.qcy.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @ClassName UserTests
 * @Description TODO
 * @Author qiancy
 * @Date 2018/10/26 14:18
 * @Version 1.0
 **/
public class UserRepositoryTest extends ApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Test
    public void testFindOne() {
        User user = userRepository.findOne(2L);
        if (this.passwordEncoder.isPasswordValid(user.getPassword(), "admin", user.getId())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            System.out.println(authentication.getDetails());
        }

//        Assert.assertEquals("waliwali", user.getName());
    }

    @Test
    public void testRole() {
        User user = userRepository.findOne(1L);
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        for (Role role : roles) {
            System.out.println(role.getName());
            System.out.println(role.getUserId());
        }
    }

}
