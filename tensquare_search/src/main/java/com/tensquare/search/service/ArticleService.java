package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/26.17 22
 * @Description:
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 添加文章
     * @param article
     */
    public void add(Article article){
        articleDao.save(article);
    }

    public Page<Article> findByTitleLike(String keyword,int page,int size){
        PageRequest pageRequest=PageRequest.of(page-1,size);
        return articleDao.findByTitleOrContentLike(keyword,keyword,pageRequest);

    }


}
