package com.story.storyadmin.domain.vo.sysmgr;

import lombok.Data;
@Data
public class UserDo {

    private Long id;

    private String name;

    public UserDo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
