package com.cc.elasticsearch;

import com.cc.elasticsearch.es.ESOrderDO;
import com.cc.elasticsearch.es.ESProductDO;
import com.cc.elasticsearch.es.OrderRepository;
import com.cc.elasticsearch.es.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchApplication.class)
@Slf4j
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test // 插入一条记录
    public void testInsert() {
        ESProductDO product = new ESProductDO();
        product.setId(3); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
        product.setName("芋道源码");
        product.setSellPoint("愿半生编码，如一生老友");
        product.setDescription("我只是一个描述");
        product.setCid(1);
        product.setCategoryName("技术");
        log.info("Insert one record into es: "+product);
        productRepository.save(product);
    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        ESProductDO product = new ESProductDO();
        product.setId(2);
        product.setCid(2);
        product.setCategoryName("技术-Java");
        productRepository.save(product);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        log.info("Delete one record from es");
        productRepository.deleteById(1);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        log.info("select one record from es");
        Optional<ESProductDO> userDO = productRepository.findById(1);
        System.out.println(userDO.get());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<ESProductDO> users = productRepository.findAllById(Arrays.asList(1, 2));
        users.forEach(System.out::println);
    }

    @Test
    public void testSaveESOrder(){
        ESOrderDO orderDO = new ESOrderDO();
        orderDO.setCreateTime(new Date());
        orderDO.setFlag(true);
        orderDO.setId(5);
        orderDO.setTests(Arrays.asList("a","bff","c"));
        log.info("save esOrder: "+orderDO);
        orderRepository.save(orderDO);
    }

    @Test
    public void testSearch1(){
        log.info("Search All esOrders");
        Iterable<ESOrderDO> allById = orderRepository.findAllById(Arrays.asList(1, 2, 3, 4, 5));
        allById.forEach(System.out::println);
    }

}