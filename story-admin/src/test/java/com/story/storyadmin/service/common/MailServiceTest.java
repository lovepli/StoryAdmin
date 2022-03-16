package com.story.storyadmin.service.common;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试发送邮件功能
 * 在系统中我们发送邮件时还需要记录发送的日志，包括发送的时间，邮件内容，状态，以及失败原因等等，这些可以自己加。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    //收件人邮箱
    private String sendTo = "1171205514@qq.com";
    /**
     * 测试简单邮件
     */
    @Test
    public void sendSimpleMail() {
        String title = "这是一封简单的邮件.";
        String content = "这是这封邮件的内容.";
        mailService.sendSimpleMail(sendTo, title, content);
        System.out.println("000");
    }

    /**
     * 测试带附件邮件
     */
    @Test
    public void sendAttachmentsMail() {
        String title = "这是一封带带附件的邮件.";
        String content ="这是这封邮件的内容.";

        List<String> attachments = new ArrayList<String>();
        attachments.add("C:\\Users\\59688\\Desktop\\tp\\tp1.jpg");
        attachments.add("C:\\Users\\59688\\Desktop\\tp\\tp2.jpg");

        mailService.sendAttachmentsMail(sendTo,title,content, attachments);
    }

    /**
     * 测试带静态资源邮件
     */
    @Test
    public void sendInlineMail() {

        String title = "这是一封带静态资源的邮件.";
        StringBuilder content = new StringBuilder();
        HashMap<String, String> resMap = new HashMap<>();

        String rsc1Id = "resource1";
        String rsc2Id = "resource2";

        resMap.put(rsc1Id, "C:\\Users\\59688\\Desktop\\tp\\tp2.jpg");
        resMap.put(rsc2Id, "C:\\Users\\59688\\Desktop\\tp\\tp2.jpg");

        content.append("<html><head><title>Title</title></head>");
        content.append("<body>");
        content.append("<image src=\'cid:" + rsc1Id + "\' /><br/>");
        content.append("<image src=\'cid:" + rsc2Id + "\' />");
        content.append("</body></html>");
        mailService.sendInlineMail(sendTo, title, content.toString(), resMap);
    }

    /**
     * 测试带附件的模板邮件
     */
    @Test
    public void sendTemplateMail() {
        String title = "这是一封带附件的模板的邮件.";
        Map<String,Object> context = new HashMap<>();
        context.put("id", "2091");
        context.put("username", "lovepli");

        List<String> attachments = new ArrayList<>();
        attachments.add("C:\\Users\\sunnj\\Desktop\\test.txt");
        attachments.add("C:\\Users\\sunnj\\Desktop\\test.rar");

        mailService.sendTemplateMail(sendTo,title,context, attachments,"test.ftl");
    }
}
