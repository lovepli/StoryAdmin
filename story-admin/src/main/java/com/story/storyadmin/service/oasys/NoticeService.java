package com.story.storyadmin.service.oasys;

import com.story.storyadmin.domain.entity.oasys.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getNotices(String username);

    void markRead(Integer[] ids);
}
