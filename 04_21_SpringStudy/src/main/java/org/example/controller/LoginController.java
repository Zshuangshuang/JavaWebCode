package org.example.controller;

import lombok.Getter;
import org.example.model.User;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller//注册一个id为类名首字母小写的bean对象
@Getter
public class LoginController {
    @Autowired
   private LoginService loginService;
    @Bean
    //此处的方法名就是id，这里表示注册一个User到容器当中去
    public User userA(){
        User user = new User();
        user.setUsername("YoYo");
        user.setPassword("123");
        return user;
    }
    @Bean
    public User userB(){
        User user = new User();
        user.setUsername("蔡文姬");
        user.setPassword("把帅气的男朋友带回家");
        return user;
    }
    @Bean
    public User userC(User userB){
        System.out.println(userB);
        User user = new User();
        user.setUsername("喵喵喵");
        user.setPassword("wanan");
        return user;
    }
}
