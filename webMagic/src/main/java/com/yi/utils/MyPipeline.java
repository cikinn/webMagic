package com.yi.utils;

import com.yi.dao.ArticleMapper;
import com.yi.entity.Article;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.InputStream;
import java.util.Date;

public class MyPipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
        // 创建mybatis的工厂创建类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 获取mybatis的配置文件流对象
        InputStream in = MyPipeline.class.getResourceAsStream("/mybatisConfig.xml");
        // 创建工厂
        SqlSessionFactory factory = builder.build(in);
        // 自动提交事务
        SqlSession sqlSession = factory.openSession(true);

        // 根据接口获取映射对象
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);

        IdWorker idWorker = new IdWorker(1,1);

        // 创建一个Article文章对象，并赋值
        Article article = new Article();
        article.setId(idWorker.nextId()+"");
        article.setTitle((String) resultItems.get("title"));
        article.setContent((String) resultItems.get("content"));
        article.setCreatetime(new Date());

        mapper.addArticle(article);


    }
}
