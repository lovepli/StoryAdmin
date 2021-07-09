package com.story.storyadmin.domain.entity.children.onetomany;


import com.story.storyadmin.domain.entity.children.DEmployee;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 分数
 */
@Data
@ToString
@NoArgsConstructor
public class DDeptBO implements  Serializable {

    private static final  long serialVersionUID =1L;

    private String id;

    private String name;

    private List<DEmployee> employees = new ArrayList<DEmployee>();

    public DDeptBO(String id) {
        this.id = id;
    }

    public String toLazyString() {
        return "Dept:{id: "+this.id+" ; name: "+this.name+"}";
    }
}