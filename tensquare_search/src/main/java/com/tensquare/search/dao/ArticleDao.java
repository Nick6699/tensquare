package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/26.17 20
 * @Description:
 */

public interface ArticleDao extends ElasticsearchRepository<Article,String>{

    /**
     * s根据文章标题和文件内容来查找数据
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}

