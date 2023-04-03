package com.guo.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
