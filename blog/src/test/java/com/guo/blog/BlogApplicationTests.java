package com.guo.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.blog.mapper.ArticleMapper;
import com.guo.blog.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void pageTest() {
        int pageNum = 2;//当前页码
        int pageSize = 3;//当前页码包含3条数据
        IPage<Article> page = new Page<>(pageNum, pageSize);

        //条件构造器,可以根据条件进行分页查询
        //LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        articleMapper.selectPage(page, null);//queryWrapper=null，则是查询所有数据进行分页


        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("当前页的包含多少条数据：" + page.getSize());
        List<Article> records = page.getRecords();
        System.out.println("当前页数据：");
        for (Article record : records) {
            System.out.println(record);
        }
    }

    @Test
    void contextLoads() {

        long pageNum = 1;
        long pageSize = 1;
        Page<Article> page = new Page<>(pageNum, pageSize);
        Page<Article> Articles = articleMapper.selectPage(page, null);
        List<Article> ArticlesList = Articles.getRecords();
        for (Article article : ArticlesList) {
            System.out.println(article);
        }
    }

    @Test
    public void testUpdate() {
        //sql自动动态配置
        Article user = new Article();
        user.setTitle("王美玲");
        user.setId(3);
        //注意：updateById的参数是一个对象
        int i = articleMapper.updateById(user);
    }

    @Test
    public void pagetest() {
        LambdaQueryWrapper<Article> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(Article::getTitle, "西");

        Page<Article> propertyPage = new Page<>(1, 2);
        IPage<Article> propertyIPage = articleMapper.selectPage(propertyPage, userLambdaQueryWrapper);
        System.out.println("总页数： " + propertyIPage.getPages());
        System.out.println("总记录数： " + propertyIPage.getTotal());
        propertyIPage.getRecords().forEach(System.out::println);
    }
    @Test
    public void selectPage(){
        Page<Article> propertyPage = new Page<>(1 , 3);
        IPage<Article> propertyIPage = articleMapper.selectPage(propertyPage , null);
        System.out.println("总页数： "+propertyIPage.getPages());
        System.out.println("总记录数： "+propertyIPage.getTotal());
        propertyIPage.getRecords().forEach(System.out::println);
    }

}
