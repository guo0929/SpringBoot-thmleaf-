package com.guo.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.blog.mapper.ArticleMapper;
import com.guo.blog.pojo.Article;
import com.guo.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class ArticleController {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/surprise")
    public String surprise(){
        return "surprise";
    }

    //分页查询文章列表
    @GetMapping("/main")
    public String pageArticle(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //新建分页构造函数Page<T> T为目标实体类
        Page<Article> userPage = new Page<Article>(pn, 3);
        //result为分页查询结果
        Page<Article> result = articleService.page(userPage);
        model.addAttribute("result",result);
        return "main";
    }


    //实现搜索功能
    @PostMapping("/search")
    public String search(Model model,String title) {
        LambdaQueryWrapper<Article> query = new LambdaQueryWrapper<>();
        query.like(Article::getTitle, title);
        List<Article> list = articleMapper.selectList(query);
        model.addAttribute("result",list);
        return "search";
    }
    @GetMapping("/toEditor")
    public String toEditor() {
        return "editor";
    }

    @RequestMapping("/toArticle")
    public String toArticle(){
        return "article";
    }
    @RequestMapping("/addArticle")
    public String addArticle(Article article) {
        articleMapper.insert(article);
        return "main";
    }
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
