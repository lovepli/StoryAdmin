package com.story.storyadmin.service.oasys.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.story.storyadmin.domain.entity.oasys.Notice;
import com.story.storyadmin.mapper.oasys.NoticeMapper;
import com.story.storyadmin.service.oasys.NoticeService;
import org.apache.commons.logging.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    private final ObjectMapper objectMapper;

    public NoticeServiceImpl(NoticeMapper noticeMapper, ObjectMapper objectMapper) {
        this.noticeMapper = noticeMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Notice> getNotices(String username) {
        return noticeMapper.selectNotices(username);
    }

    @Override
    public void markRead(Integer[] ids) {
        noticeMapper.markRead(ids);
    }

    /**
     * https://www.cnblogs.com/toutou/archive/2019/10/07/11354330.html
     * 监听服务器上的kafka是否有相关的消息发过来
     * 定义此消费者接收topics = "notice"的消息，与controller中的topic对应上即可
     * @param cr 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     * @throws IOException
     */
    // @KafkaListener(topics = "notice")
    public void onListenNotice(ConsumerRecord<String, String> cr) throws IOException {
        //判断是否为null
        //Optional<?> kafkaMessage = Optional.ofNullable(cr.value());
        //System.out.println(">>>>>>>>>> record =" + kafkaMessage);
        //if(kafkaMessage.isPresent()){
        //    //得到Optional实例中的值
        //    Object message = kafkaMessage.get();
        //    System.err.println("消费消息:"+message);
        //}
        System.out.printf("topic is %s, offset is %d, value is %s \n", cr.topic(), cr.offset(), cr.value());

        Notice notice = null;
        try {
            notice = objectMapper.readValue(cr.value(), Notice.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        noticeMapper.insetNotice(notice);
    }
}
