package com.guo.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.blog.mapper.AdminMapper;
import com.guo.blog.mapper.ArticleMapper;
import com.guo.blog.mapper.CategoryMapper;
import com.guo.blog.mapper.UserMapper;
import com.guo.blog.pojo.Admin;
import com.guo.blog.pojo.Article;
import com.guo.blog.pojo.Category;
import com.guo.blog.pojo.User;
import com.guo.blog.service.AdminService;
import com.guo.blog.service.ArticleService;
import com.guo.blog.service.CategoryService;
import com.guo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog/admin")
public class AdminController {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;


    //跳转文章修改页面，通过id把要修改的数据全部展示出来
    @RequestMapping("/toUpdateArticle")
    public String toUpdateArticle(Integer id, Model model){
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, id);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        model.addAttribute("articleInfo",articleList);
        return "/admin/blog-edit";
    }

    //修改文章信息
    @RequestMapping("/updateArticle")
    public String updateArticle(Integer id, String title, String tag, String summary, String type, String readNumber, String thumbUpNumber, Date createTime){
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,id).
                set(Article::getTitle,title).
                set(Article::getTag,tag).
                set(Article::getSummary,summary).
                set(Article::getReadNumber,readNumber).
                set(Article::getThumbUpNumber,thumbUpNumber).
                set(Article::getType,type).
                set(Article::getCreateTime,createTime);
        articleMapper.update(null,updateWrapper);
        return "redirect:/blog/admin/pageArticle";
    }

    //通过id删除文章
    @RequestMapping("/deleteArticle")
    public String deleteArticle(Integer id){
        articleMapper.deleteById(id);
        System.out.println("删除成功！");
        return "redirect:/blog/admin/pageArticle";
    }

    //新增文章
    @RequestMapping("/addArticle")
    public String addArticle(Article article){
        articleMapper.insert(article);
        return "redirect:/blog/admin/pageArticle";
    }

    //分页查询文章信息
    @GetMapping("/pageArticle")
    public String pageArticle(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //新建分页构造函数Page<T> T为目标实体类
        Page<Article> articlePage = new Page<Article>(pn, 3);
        //result为分页查询结果
        Page<Article> result = articleService.page(articlePage);
        model.addAttribute("ArticleResult",result);
        return "/admin/blog-list";
    }

    //搜索文章信息
    @PostMapping("/ArticleSearch")
    public String ArticleSearch(Model model,String title) {
        LambdaQueryWrapper<Article> query = new LambdaQueryWrapper<>();
        query.like(Article::getTitle, title);
        List<Article> articleList = articleMapper.selectList(query);
        model.addAttribute("result",articleList);
        return "/admin/ArticleSearch";
    }

    //新增管理员
    @RequestMapping("/addAdmin")
    public String adminAdd(Admin admin){
        adminMapper.insert(admin);
        return "redirect:/blog/admin/pageAdmin";
    }

    //跳转管理员修改页面，通过id把要修改的数据全部展示出来
    @RequestMapping("/toUpdateAdmin")
    public String toUpdateAdmin(Integer id, Model model){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getId, id);
        List<Admin> adminList = adminMapper.selectList(queryWrapper);
        model.addAttribute("adminInfo",adminList);
        return "/admin/admin-edit";
    }

    //修改管理员信息
    @RequestMapping("/updateAdmin")
    public String updateAdmin(String name,Integer id,String phone,String email,String status){
        LambdaUpdateWrapper<Admin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Admin::getId,id).
                set(Admin::getEmail,email).
                set(Admin::getName,name).
                set(Admin::getPhone,phone).
                set(Admin::getStatus,status);
        adminMapper.update(null,updateWrapper);
        return "redirect:/blog/admin/pageAdmin";
    }

    //通过id删除管理员
    @RequestMapping("/deleteAdmin")
    public String deleteAdmin(Integer id){
        adminMapper.deleteById(id);
        System.out.println("删除成功！");
        return "redirect:/blog/admin/pageAdmin";
    }

    //分页查询管理员信息
    @GetMapping("/pageAdmin")
    public String pageAdmin(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //新建分页构造函数Page<T> T为目标实体类
        Page<Admin> adminPage = new Page<Admin>(pn, 3);
        //result为分页查询结果
        Page<Admin> result = adminService.page(adminPage);
        model.addAttribute("AdminResult",result);
        return "/admin/admin-list";
    }
    //搜索管理员信息
    @PostMapping("/AdminSearch")
    public String AdminSearch(Model model,String name) {
        LambdaQueryWrapper<Admin> query = new LambdaQueryWrapper<>();
        query.like(Admin::getName, name);
        List<Admin> adminList = adminMapper.selectList(query);
        model.addAttribute("result",adminList);
        return "/admin/AdminSearch";
    }

    //新增用户
    @RequestMapping("/addMember")
    public String memberAdd(User user){
        userMapper.insert(user);
        return "redirect:/blog/admin/pageMember";
    }

    //跳转用户修改页面，通过id把要修改的数据全部展示出来
    @RequestMapping("/toUpdateMember")
    public String toUpdateMember(Integer id, Model model){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, id);
        List<User> userList = userMapper.selectList(queryWrapper);
        model.addAttribute("userInfo",userList);
        return "/admin/member-edit";
    }
    //修改用户信息
    @RequestMapping("/updateMember")
    public String updateMember(String username,Integer id,String password){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,id).
                set(User::getUsername,username).
                set(User::getPassword,password);
        userMapper.update(null,updateWrapper);
        return "redirect:/blog/admin/pageMember";
    }

    //通过id删除用户
    @RequestMapping("/deleteMember")
    public String deleteMember(Integer id){
        userMapper.deleteById(id);
        System.out.println("删除成功！");
        return "redirect:/blog/admin/pageMember";
    }

    //分页查询用户信息
    @GetMapping("/pageMember")
    public String pageMember(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //新建分页构造函数Page<T> T为目标实体类
        Page<User> userPage = new Page<User>(pn, 3);
        //result为分页查询结果
        Page<User> result = userService.page(userPage);
        model.addAttribute("MemberResult",result);
        return "/admin/member-list";
    }

    //搜索用户信息
    @PostMapping("/MemberSearch")
    public String MemberSearch(Model model,String username) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.like(User::getUsername, username);
        List<User> userlist = userMapper.selectList(query);
        model.addAttribute("result",userlist);
        return "/admin/MemberSearch";
    }

//    //批量删除
//    //用户
//    @RequestMapping("/membersdel")
//    public String membersdel(ArrayList<Article> idList){
//        userMapper.deleteBatchIds(idList);
//        return "redirect:/blog/admin/pageMember";
//    }


    //分页查询分类信息
    @GetMapping("/pageCategory")
    public String pageCategory(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //新建分页构造函数Page<T> T为目标实体类
        Page<Category> categoryPage = new Page<Category>(pn, 3);
        //result为分页查询结果
        Page<Category> result = categoryService.page(categoryPage);
        model.addAttribute("CategoryResult",result);
        return "/admin/category-list";
    }

    //新增分类信息
    @RequestMapping("/AddCategory")
    public String AddCategory(Category category){
        categoryMapper.insert(category);
        return "redirect:/blog/admin/pageCategory";
    }

    //跳转分类修改页面，通过id把要修改的数据全部展示出来
    @RequestMapping("/toUpdateCategory")
    public String toUpdateCategory(Integer id, Model model){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId, id);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        model.addAttribute("categoryInfo",categories);
        return "/admin/category-edit";
    }
    //修改分类信息
    @RequestMapping("/updateCategory")
    public String updateCategory(String type,Integer id,String tag){
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Category::getId,id).
                set(Category::getType,type).
                set(Category::getTag,tag);
        categoryMapper.update(null,updateWrapper);
        return "redirect:/blog/admin/pageCategory";
    }

    //通过id删除分类信息
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Integer id){
        categoryMapper.deleteById(id);
        System.out.println("删除成功！");
        return "redirect:/blog/admin/pageCategory";
    }
}
