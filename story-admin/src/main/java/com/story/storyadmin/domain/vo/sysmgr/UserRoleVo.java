package com.story.storyadmin.domain.vo.sysmgr;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * <p>
 *  用户角色 一对多 角色表单对象
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
public class UserRoleVo {
    /** 用户ID*/
    @NotBlank(message = "用户id不能为空")
    private Long userId;
    /** 角色集合 */
    private Set<Long> roleIds;
}
