package com.story.storyadmin.service.oasys.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.story.storyadmin.domain.entity.oasys.Leave;
import com.story.storyadmin.domain.entity.oasys.Notice;
import com.story.storyadmin.domain.vo.oasys.LeaveVo;
import com.story.storyadmin.mapper.oasys.LeaveMapper;
import com.story.storyadmin.service.oasys.LeaveService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveMapper leaveMapper;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public LeaveServiceImpl(LeaveMapper leaveMapper, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.leaveMapper = leaveMapper;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void askLeave(Leave leave) {
        leave.setStatus(0);
        leaveMapper.askLeave(leave);
    }

    @Override
    public void checkLeave(Leave leave) {
       //  String username = leave.getUser().getUsername();
        String username = "admin" ;
        Notice notice = new Notice();
        notice.setReceiverName(username);
        LocalDateTime time = LocalDateTime.now();
        notice.setCreatedTime(time);
        if (leave.getStatus() == 1) {
            notice.setContent("你的请假通过了审核！");
        } else {
            notice.setContent("你的请假没有通过审核！");
        }
        String json = null;
        try {
            json = objectMapper.writeValueAsString(notice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 请假审批 将请假的结果通过消息发送出去的同时修改数据库请假记录的状态,这里的topic可以写在配置文件中引入
        kafkaTemplate.send("notice", json); //通过KafkaTemplate模板类发送数据
        leaveMapper.checkLeave(leave);
    }

    @Override
    public PageInfo<LeaveVo> getLeaves(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(leaveMapper.selectLeaves());
    }
}
