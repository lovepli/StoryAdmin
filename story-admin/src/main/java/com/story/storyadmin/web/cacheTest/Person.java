package com.story.storyadmin.web.cacheTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: lipan
 * @date: 2020/7/3
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = -8183942491930372236L;
    private Long userId;
    private String username;
    private String password;
}