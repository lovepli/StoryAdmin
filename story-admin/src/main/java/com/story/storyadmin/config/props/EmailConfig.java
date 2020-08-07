package com.story.storyadmin.config.props;

import org.springframework.stereotype.Component;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;

/**
 * 邮件配置类
 */
@Data
@Component
public class EmailConfig {
	/** 
     * 发件邮箱 
     */  
    @Value("${spring.mail.mailFrom}")  
    private String emailFrom;  
  
}
