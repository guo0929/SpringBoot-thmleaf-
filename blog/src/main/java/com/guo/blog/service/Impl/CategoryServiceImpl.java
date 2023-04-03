package com.guo.blog.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.blog.mapper.CategoryMapper;
import com.guo.blog.pojo.Category;
import com.guo.blog.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
