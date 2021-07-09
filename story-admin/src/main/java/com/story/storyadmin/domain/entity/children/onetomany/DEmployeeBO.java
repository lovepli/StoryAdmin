package com.story.storyadmin.domain.entity.children.onetomany;

import com.story.storyadmin.domain.entity.children.DDept;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description:
 */
@Data
@ToString
@NoArgsConstructor
public class DEmployeeBO implements Serializable {

    private static final  long serialVersionUID =1L;

    protected String id;

    protected String name;

    protected DDept dept; // 数据库表对象

    public DEmployeeBO(String id) {
        this.id = id;
    }

    public String toLazyString() {
        return "Employee:{id: " + this.id + "; name: " + this.name + "}";
    }

    @Override
    public String toString() {
        return "Employee(id=" + this.id + ", name=" + this.name + ", dept={id=" + this.dept.getId() + ", name="
                + this.dept.getName() + "})";
    }
}
