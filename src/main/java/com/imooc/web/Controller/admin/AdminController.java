package com.imooc.web.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author qiancy
 * @Date 2018/11/1 12:44
 * @Version 1.0
 **/
@Controller
public class AdminController {

    @GetMapping("/admin/center")
    public String showAdminCenterPage(){
        return "admin/center";
    }

    @GetMapping("admin/welcome")
    public String showWelcomePage(){
        return "admin/welcome";
    }

    @GetMapping("admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }
}
