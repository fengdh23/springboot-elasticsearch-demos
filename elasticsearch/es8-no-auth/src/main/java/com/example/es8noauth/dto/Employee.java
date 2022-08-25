/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.es8noauth.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

/**
 * @author Artur Konczak
 * @author Oliver Gierke
 * @author Christoph Strobl
 */
@Data
@Document(indexName = "employee-index",createIndex = true)
public class Employee {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String position;
    private String country;
    @Field(type = Date)
    private java.util.Date joinDate;
    private BigDecimal salary;

}
