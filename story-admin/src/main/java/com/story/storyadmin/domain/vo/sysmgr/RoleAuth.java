package com.story.storyadmin.domain.vo.sysmgr;

import lombok.Data;

import java.util.Set;

/**
 * <p>
 * 角色权限 一对多 权限表单对象
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
public class RoleAuth {
    /** 角色ID */
    private Long roleId;
    /** 权限ID */
    private Set<Long> authorityIds;
}
