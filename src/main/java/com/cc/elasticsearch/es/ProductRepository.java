package com.cc.elasticsearch.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author cc
 */
public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

}