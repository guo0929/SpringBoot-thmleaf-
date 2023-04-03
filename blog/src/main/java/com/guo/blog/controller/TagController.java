package com.guo.blog.controller;


import com.guo.blog.mapper.ArticleMapper;
import com.guo.blog.mapper.TagMapper;
import com.guo.blog.pojo.Article;
import com.guo.blog.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class TagController {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/tags")
    public String tags(Model model){
        List<Tag> tags = tagMapper.selectList(null);
        model.addAttribute("tags",tags);
        return "tag";
    }
    @RequestMapping("/tagshow")
    public String tagShow(Model model){
        List<Article> tagShow = articleMapper.selectList(null);
        model.addAttribute("tagShow",tagShow);
        return "tagshow";
    }
}
