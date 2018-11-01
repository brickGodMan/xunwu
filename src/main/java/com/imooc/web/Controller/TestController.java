package com.imooc.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName TestController
 * @Description 测试
 * @Author qiancy
 * @Date 2018/10/29 9:47
 * @Version 1.0
 **/
@Controller
public class TestController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","qiancy");
        return "index";
    }

    @GetMapping("/500")
    public String internalServerError(){
        return "500";
    }

    @GetMapping("/403")
    public String forbidden(){
        return "403";
    }
}
