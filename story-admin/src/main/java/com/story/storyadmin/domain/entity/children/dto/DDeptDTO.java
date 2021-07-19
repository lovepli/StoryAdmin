package com.story.storyadmin.domain.entity.children.dto;

import com.story.storyadmin.domain.entity.children.DDept;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description: 部门 Mybatis中使用association及collection进行自关联示例（含XML版与注解版）
 */
@Data
@ToString
@NoArgsConstructor
public class DDeptDTO  implements Serializable {

    private static final  long serialVersionUID =1L;

    private String id;

    private String name;

    private List<DDept> children =new ArrayList<>();

    private DDept parent;

    public String toLazyString() {
        return "Dept:{id: " + this.id + " ; name: " + this.name + "}";
    }

}
