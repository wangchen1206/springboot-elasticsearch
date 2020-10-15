package com.cc.elasticsearch.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/10/10
 */
@Document(indexName = "order",
type = "order",
        shards = 1, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
@Data
public class ESOrderDO {

    @Id
    private Integer id;

    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD,type = FieldType.Text,searchAnalyzer = FieldAnalyzer.IK_MAX_WORD)
    private String productName;

    private List<String> tests;

    @Field(analyzer = FieldAnalyzer.IK_SMART,type = FieldType.Date)
    private Date createTime;

    private Boolean flag;
}
