package com.story.storyadmin.service.common;

import java.util.List;
import java.util.Map;

/**
 * 定义发送邮件接口
 *
 * 发送邮件算是项目里常用的功能，大体上可以分为四类：
 * 1. 只有文本的简单有邮件
 * 2. 带附件的邮件
 * 3. 带静态资源的邮件
 * 4. 以及带附件的模板邮件。
 * SpringBoot中集成邮件功能还是非常简单的，主要依赖spring-boot-starter-mail就够了。
 */
public interface MailService {
	/**
	 * 发送简单邮件 
	 * @param sendTo 收件人
	 * @param titel 标题
	 * @param content 内容
	 */
    void sendSimpleMail(String sendTo, String titel, String content);
    /**
     * 发送简单带附件邮件 
     * @param sendTo 收件人
     * @param titel 标题
     * @param content 内容
     * @param attachments 附件路径
     */
    void sendAttachmentsMail(String sendTo, String titel, String content, List<String> attachments);
    /**
     * 发送内嵌静态资源邮件
     * @param sendTo 收件人
     * @param titel 标题
     * @param content 内容
     * @param attachments 资源id及路径
     */
    void sendInlineMail(String sendTo, String titel, String content, Map<String, String> attachments);
    /**
     * 发送模板邮件 
     * @param sendTo 收件人
     * @param titel 标题
     * @param context 内容参数
     * @param attachments 附件
     * @param templateName 模板路径
     */
    void sendTemplateMail(String sendTo, String titel, Map<String,Object> context, List<String> attachments, String templateName);
}
