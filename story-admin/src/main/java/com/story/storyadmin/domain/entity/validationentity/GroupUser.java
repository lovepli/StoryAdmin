package com.story.storyadmin.domain.entity.validationentity;


import com.story.storyadmin.validator.group2.GroupA;
import com.story.storyadmin.validator.group2.GroupB;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

/**
 * @author: 59688
 * @date: 2021/7/15
 * @description:
 *
 * GroupA校验字段userId
 * GroupB校验字段userName、sex
 * Default校验字段age(Default使Validator自带的默认分组)
 */
@Data
public class GroupUser {
    @NotBlank
    @Range(min = 1,max = Integer.MAX_VALUE,message = "必须大于0",groups = {GroupA.class})
    /**用户id*/
    private Integer userId;
    @NotBlank
    @Length(min = 4,max = 20,message = "必须在[4,20]",groups = {GroupB.class})
    /**用户名*/
    private String userName;
    @NotBlank
    @Range(min = 0,max = 100,message = "年龄必须在[0,100]",groups={Default.class})
    /**年龄*/
    private Integer age;
    @Range(min = 0,max = 2,message = "性别必须在[0,2]",groups = {GroupB.class})
    /**性别 0：未知；1：男；2：女*/
    private Integer sex;
}
