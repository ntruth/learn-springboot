package com.ucmed.elasticsearch;

// ProductRepository04Test.java

import com.alibaba.fastjson.JSONObject;
import com.ucmed.elasticsearch.model.ProductConditionBO;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepository02Test {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

//    简单来说下这个方法的整体逻辑，根据关键字检索 name、sellPoint、categoryName 字段，聚合 cid 返回。
//<1> 处，创建 NativeSearchQueryBuilder 对象，并设置查询的索引是 product ，即 ESProductDO 类的对应的索引。
//    筛选条件
//<2> 处，根据关键字检索 name、sellPoint、categoryName 字段。此处，我们使用的关键字是 "芋道" 。
//    聚合
//<3> 处，将商品分类编号 cid 聚合成 cids 返回。
//    如果 ESProductDO 上有品牌编号，我们可以多在聚合一个品牌编号返回。
//    执行搜索
//<4> 处，执行查询，解析聚合结果，设置回 ProductConditionBO 中。
//<5> 处，后续遍历 condition.categories 数组，查询商品分类，设置商品分类名。
    @Test
    public void test() {
        // <1> 创建 ES 搜索条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withIndices("product");;
        // <2> 筛选
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("芋道",
                "name", "sellPoint", "categoryName"));
        // <3> 聚合
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid")); // 商品分类
        // <4> 执行查询
        ProductConditionBO condition = elasticsearchTemplate.query(nativeSearchQueryBuilder.build(), response -> {
            ProductConditionBO result = new ProductConditionBO();
            // categoryIds 聚合
            Aggregation categoryIdsAggregation = response.getAggregations().get("cids");
            if (categoryIdsAggregation != null) {
                result.setCategories(new ArrayList<>());
                for (LongTerms.Bucket bucket  : (((LongTerms) categoryIdsAggregation).getBuckets())) {
                    result.getCategories().add(new ProductConditionBO.Category().setId(bucket.getKeyAsNumber().intValue()));
                }
            }
            // 返回结果
            return result;
        });
        // <5> 后续遍历 condition.categories 数组，查询商品分类，设置商品分类名。
        System.out.println(JSONObject.toJSONString(condition));
    }

}

