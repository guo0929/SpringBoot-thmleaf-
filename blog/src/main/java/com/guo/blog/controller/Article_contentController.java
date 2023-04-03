package com.guo.blog.controller;

import com.guo.blog.mapper.ArticleMapper;
import com.guo.blog.mapper.Article_contentMapper;
import com.guo.blog.pojo.Article;
import com.guo.blog.pojo.Article_content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class Article_contentController {

    @Autowired
    private Article_contentMapper contentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/content")
    public String content(Model model){
        List<Article_content> contents = contentMapper.selectList(null);
        model.addAttribute("contents",contents);
        return "article";
    }
    @RequestMapping("/list")
    public String list(Model model){
        List<Article> list = articleMapper.selectList(null);
        model.addAttribute("list",list);
        return "article";
    }

}
