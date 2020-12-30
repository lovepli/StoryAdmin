package com.story.storyadmin.domain.vo.oasys;

import com.story.storyadmin.domain.entity.oasys.Leave;
import lombok.Data;

@Data
public class LeaveVo extends Leave {
    /** 用户名 */
    private String userName;

}
