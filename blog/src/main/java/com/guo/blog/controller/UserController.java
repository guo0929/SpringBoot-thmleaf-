package com.guo.blog.controller;


import com.guo.blog.mapper.UserMapper;
import com.guo.blog.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String str(String username, String password, Model model,HttpSession session) {
        User user = userMapper.login(username, password);
        if(user==null){
            return "404";
        }
        System.out.println("登陆成功！");
        session.setAttribute("login",user);
        return "redirect:/blog/main";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/register")
    public String register(String username,String password){
        userMapper.saveInfo(username,password);
        return "login";
    }
}
