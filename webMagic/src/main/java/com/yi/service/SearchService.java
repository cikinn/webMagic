package com.yi.service;

import com.yi.dao.SearchDao;
import com.yi.entity.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private SearchDao searchDao;

    public Iterable<Search> findAll(){
        return searchDao.findAll();
    }
}
