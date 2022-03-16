package com.story.storyadmin.web.versionController;

import lombok.Data;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
@Data
public class StudentV2 {
    private Name name;

    public StudentV2(Name name) {
        this.name = name;
    }
}