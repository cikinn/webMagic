package com.yi.utils;

import com.yi.dao.ArticleMapper;
import com.yi.entity.Article;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    @Test
    public void test(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        InputStream in = MyPipeline.class.getResourceAsStream("/mybatisConfig.xml");
        SqlSessionFactory factory = builder.build(in);

        SqlSession sqlSession = factory.openSession(true);

        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);

        Article article = new Article();

        IdWorker idWorker = new IdWorker(1,1);
        article.setId(idWorker.nextId()+"");
        article.setTitle("aaa");
        article.setContent("aaa");
        article.setCreatetime(new Date());

        mapper.addArticle(article);

        System.out.println("成功");

    }
}
