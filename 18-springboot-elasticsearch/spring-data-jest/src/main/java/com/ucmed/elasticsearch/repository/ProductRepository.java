package com.ucmed.elasticsearch.repository;

import com.ucmed.elasticsearch.model.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

}