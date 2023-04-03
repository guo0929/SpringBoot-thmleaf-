package com.guo.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

        //登录
        @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
        User login(@Param("username") String username, @Param("password") String password);

        //注册
        @Insert("insert into user (username,password) values (#{username},#{password})")
        void saveInfo(@Param("username") String username,@Param("password") String password);
}

