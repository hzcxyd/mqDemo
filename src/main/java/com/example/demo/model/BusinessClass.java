package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: BusinessClass
 * Description:
 * date: 6/17/23 2:57 PM
 *
 * @author chenbo
 * @since JDK 1.8
 */
@Data
public class BusinessClass implements Serializable {
    private String id;
    private String name;
    private String content;
}
