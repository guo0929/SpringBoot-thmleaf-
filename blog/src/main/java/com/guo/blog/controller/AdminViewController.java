package com.guo.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.blog.mapper.AdminMapper;
import com.guo.blog.mapper.ArticleMapper;
import com.guo.blog.mapper.UserMapper;
import com.guo.blog.pojo.Admin;
import com.guo.blog.pojo.Article;
import com.guo.blog.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blog/admin")
public class AdminViewController {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;


    @RequestMapping("/qianindex")
    public String index(){
        return "index";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/admin/login";
    }
    @RequestMapping("/login")
    public String str(String username, String password, Model model, HttpSession session) {
        User user = userMapper.login(username, password);
        if(user==null){
            return "/admin/login";
        }
        System.out.println("登陆成功！");
        session.setAttribute("login",user);
        return "redirect:/blog/admin/index";
    }
    @RequestMapping("/index")
    public String admin(){
        return "/admin/index";
    }
    @RequestMapping("/desktop")
    public String desktop(){
        return "/admin/welcome";
    }
    @RequestMapping("/category-add")
    public String categoryAdd(){
        return "admin/category-add";
    }
    @RequestMapping("/blog-add")
    public String blogAdd(){
        return "admin/blog-add";
    }
    @RequestMapping("/member-add")
    public String memberAdd(){
        return "admin/member-add";
    }
    @RequestMapping("/admin-add")
    public String toAdminAdd(){
        return "/admin/admin-add";
    }
}
