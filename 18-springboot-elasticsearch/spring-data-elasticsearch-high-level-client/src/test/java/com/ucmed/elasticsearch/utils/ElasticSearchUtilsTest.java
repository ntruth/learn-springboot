package com.ucmed.elasticsearch.utils;

import com.ucmed.elasticsearch.model.Book;
import com.ucmed.elasticsearch.model.EsEntity;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchUtilsTest {

    @Autowired
    ElasticSearchUtils elasticSearchUtils;

    // 获取某一个
    @Test
    public void testQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(new TermQueryBuilder("id", 1));
        List<Book> res = elasticSearchUtils.search(elasticSearchUtils.INDEX_NAME, builder, Book.class);
        if (res.size() > 0) {
            System.out.println(res.get(0));
        }
    }

    // 获取全部
    @Test
    public void testGetAll() {
        System.out.println(elasticSearchUtils.search(ElasticSearchUtils.INDEX_NAME, new SearchSourceBuilder(), Book.class));
    }

    //根据关键词搜索某用户下的书
    @Test
    public void searchByUserIdAndName() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //boolQueryBuilder.must(QueryBuilders.termQuery("userId", userId));
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", "芋道源码"));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(10).query(boolQueryBuilder);
        System.out.println(elasticSearchUtils.search(elasticSearchUtils.INDEX_NAME, builder, Book.class));
    }

}