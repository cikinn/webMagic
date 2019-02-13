package com.yi.dao;

import com.yi.entity.Search;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SearchDao extends ElasticsearchRepository<Search,String> {

    List<Search> findByContentLike(String content);
}
