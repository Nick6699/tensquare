package com.tensquare.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Autoer: Nick Chen
 * @Date: 2019/7/26.16 56
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "tensquare",type = "article")
public class Article {
    @Id
    private String id; //id
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer="searchAnalyzer")
    private String title; //标题
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer="searchAnalyzer")
    private String content; //内容
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer="searchAnalyzer")
    private String state; //状态
}
