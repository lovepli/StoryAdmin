package com.story.storyadmin.domain.entity.oasys;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Alias("Leave")
public class Leave implements Serializable {
    /**主键 */
    private Long id;
    /**申请人 */
    private Long userId;
    /**开始时间 */
    private LocalDate beginDate;
    /**结束时间 */
    private LocalDate endDate;
    /**请假原因 */
    private String reason;
    /**审批意见 */
    private String comment;
    /**请假类型 */
    private String type;
    /** 审核状态 0 未审核 1 通过 2 拒绝 */
    private int status;
    private static final long serialVersionUID = 1L;
}
