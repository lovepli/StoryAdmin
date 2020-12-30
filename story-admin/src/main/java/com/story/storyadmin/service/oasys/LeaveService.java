package com.story.storyadmin.service.oasys;

import com.github.pagehelper.PageInfo;
import com.story.storyadmin.domain.entity.oasys.Leave;
import com.story.storyadmin.domain.vo.oasys.LeaveVo;

public interface LeaveService {
    void askLeave(Leave leave);

    void checkLeave(Leave leave);

    PageInfo<LeaveVo> getLeaves(int pageNumber, int pageSize);
}
