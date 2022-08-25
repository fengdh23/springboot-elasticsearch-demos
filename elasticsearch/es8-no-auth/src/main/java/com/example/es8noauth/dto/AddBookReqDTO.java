package com.example.es8noauth.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * analyzer = "ik_max_word",
 */
@Data
@Document(indexName = "book-index")
public class AddBookReqDTO {
    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String author;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date createTime;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date updateTime;
}
