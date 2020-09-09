package com.story.storyadmin.config.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户信息
 * @author sunnj
 */
@Data
public class LoginUser implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 主键ID */
  public Long userId;
  /** 账号 */
  public String account;
  /** 姓名 */
  public String name;
  /** 组织ID */
  public Long orgId;

  public LoginUser() {}

  public LoginUser(String account) {
    this.account = account;
  }

  public LoginUser(Long userId, String account, String name, Long orgId) {
    this.userId = userId;
    this.account = account;
    this.name = name;
    this.orgId = orgId;
  }
}
