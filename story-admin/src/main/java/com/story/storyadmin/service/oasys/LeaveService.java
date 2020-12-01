package com.story.storyadmin.service.oasys;

import com.github.pagehelper.PageInfo;
import com.story.storyadmin.domain.entity.oasys.Leave;

public interface LeaveService {
    void askLeave(Leave leave);

    void checkLeave(Leave leave);

    PageInfo<Leave> getLeaves(int pageNumber, int pageSize);
}
