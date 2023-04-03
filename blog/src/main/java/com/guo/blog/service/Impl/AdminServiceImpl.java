package com.guo.blog.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.blog.mapper.AdminMapper;
import com.guo.blog.pojo.Admin;
import com.guo.blog.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
