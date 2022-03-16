package com.story.storyadmin.myDatastucture.tree.BPlusTree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: 59688
 * @date: 2021/10/8
 * @description:
 */
@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Double price;
}
