package com.guo.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
