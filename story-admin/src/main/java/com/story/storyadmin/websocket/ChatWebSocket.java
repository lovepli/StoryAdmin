package com.story.storyadmin.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.domain.entity.oasys.Message;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.security.Principal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@ServerEndpoint("/chat")
public class ChatWebSocket {
    public static ConcurrentMap<String, ChatWebSocket> webSockets = new ConcurrentHashMap<>();
    // 新建消息类
    public static Message message = new Message();
    public static ObjectMapper objectMapper;
    public static UserMapper userMapper;
    public static int id;
    public Session session;
    public String username;
    public String avatar;

    @Autowired
    public void setChatWebSocket(ObjectMapper objectMapper, UserMapper userMapper) {
        ChatWebSocket.userMapper = userMapper;
        ChatWebSocket.objectMapper = objectMapper;
    }

    @OnOpen
    public void onOpen(Session session) {
        Principal principal = session.getUserPrincipal();
        if (principal != null) {
            //String username = principal.getName();
            //User user = userMapper.selectUserByUsername(username);
            // TODO
            User user  =userMapper.selectById(UserContext.getCurrentUser().getUserId());
            //this.username = username;
            this.username = user.getName();
            this.avatar = user.getAvatar();
            this.session = session;
            webSockets.put(username, this);
        }
    }

    @OnClose
    public void onClose(Session session) {
        Principal principal = session.getUserPrincipal();
        if (principal != null) {
            webSockets.remove(principal.getName());
        }
    }

    @OnMessage
    public void onMessage(String content) {
        message.setId(id++);
        message.setUsername(username);
        message.setAvatar(avatar);
        message.setContent(content);
        try {
            content = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (ChatWebSocket webSocket : webSockets.values()) {
            webSocket.session.getAsyncRemote().sendText(content);
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }
}
