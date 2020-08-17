package com.story.storyadmin.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 数据库备份配置
 *
 * @author sunnj
 */
@ConfigurationProperties(prefix = "dbbackup")
@Component
@Data
public class DbBackupProperties {
  /** 服务器ip地址 */
  private String servername;
  /** 数据库名 */
  private String dbname;
  /** 用户名 */
  private String username;
  /** 密码 */
  private String password;
  /** 数据库安装路径 */
  private String installPath;
  /** 数据库备份路径 */
  private String backupPath;
}
