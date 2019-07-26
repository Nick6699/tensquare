package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/26.17 23
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
      * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleService.add(article);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     * 在标题和内容里搜索
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findByTitleLike(@PathVariable String keyword,@PathVariable int page,@PathVariable int size){
        Page<Article> pageData=articleService.findByTitleLike(keyword,page,size);
        return new Result(true,StatusCode.OK,"查找成功",new PageResult<Article>(pageData.getTotalElements(),pageData.getContent()));
    }
}
