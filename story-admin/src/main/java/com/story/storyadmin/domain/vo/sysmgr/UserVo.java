package com.story.storyadmin.domain.vo.sysmgr;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录表单对象
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    /** 用户jpg图像 */
    private String avatar;

}
