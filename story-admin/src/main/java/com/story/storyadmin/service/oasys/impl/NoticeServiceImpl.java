package com.story.storyadmin.service.oasys.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.story.storyadmin.domain.entity.oasys.Notice;
import com.story.storyadmin.mapper.oasys.NoticeMapper;
import com.story.storyadmin.service.oasys.NoticeService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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

    //@KafkaListener(topics = "notice")
    public void onListenNotice(ConsumerRecord<String, String> cr) throws IOException {
        Notice notice = null;
        try {
            notice = objectMapper.readValue(cr.value(), Notice.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        noticeMapper.insetNotice(notice);
    }
}
