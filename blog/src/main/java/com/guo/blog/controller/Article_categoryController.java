package com.guo.blog.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.blog.mapper.ArticleMapper;
import com.guo.blog.mapper.Article_categoryMapper;
import com.guo.blog.pojo.Article;
import com.guo.blog.pojo.Article_category;
import com.guo.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/blog")
public class Article_categoryController {

    @Autowired
    private Article_categoryMapper article_category;
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/category")
    public String findAll(Model model){
        List<Article_category> list = article_category.selectList(null);
        model.addAttribute("category",list);
        return "category";
    }
    @RequestMapping("/categoryshow")
    public String categoryshow(Model model){
        List<Article> categoryshow = articleMapper.selectList(null);
        model.addAttribute("categorys",categoryshow);
        return "categoryshow";
    }
}
