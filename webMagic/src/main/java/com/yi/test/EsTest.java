package com.yi.test;

import com.yi.dao.ArticleMapper;
import com.yi.dao.SearchDao;
import com.yi.entity.Article;
import com.yi.entity.Search;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.aggregations.Aggregations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EsTest {

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void test1() {
        template.createIndex(Search.class);
    }

    @Test
    public void test2() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream in = EsTest.class.getResourceAsStream("/mybatisConfig.xml");
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession = factory.openSession(true);
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articleList = mapper.findAll();

        for (Article article : articleList) {
            Search search = new Search();
            search.setId(article.getId());
            search.setTitle(article.getTitle());
            search.setContent(article.getContent());
            search.setCreatetime(article.getCreatetime());
            searchDao.save(search);
        }

    }

    @Test
    public void test3() {
        template.createIndex(Search.class);
    }

    @Test
    public void test4() {
        Search search = new Search();
        search.setTitle("aaa");
        search.setContent("sss");
        searchDao.save(search);
    }

    @Test
    public void test5() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.DEFAULT)
                .withIndices("cikinn").withTypes("article")
                .addAggregation(terms("subjects").field("subject"))
                .build();
        // when
        Aggregations aggregations = template.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse response) {
                return response.getAggregations();
            }
        });
        // then
        assertThat(aggregations, is(notNullValue()));
        assertThat(aggregations.asMap().get("subjects"), is(notNullValue()));
    }

    @Test
    public void test6() {
       /* Integer a = 1;
        Integer b = 1;
        Integer c = 100;
        Integer d = 100;
        System.out.println(a == b);
        System.out.println(c == d);*/
        System.out.println(1000 * 60 * 60 * 24 * 60 * 60 * 24 * 30);
    }
}
