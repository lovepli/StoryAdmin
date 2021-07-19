package com.story.storyadmin.domain.entity.oasys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Alias("Attendance")
public class Attendance implements Serializable {
    private Long id;
    /**用户ID */
    private Long userId;
    /**当前日期 */
    private LocalDate signInDate;
    /**签到时间 */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime signInTime;
    /**签退时间 */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime signOutTime;
    /** 是否签退 */
    private boolean hasSignOut;
    private static final long serialVersionUID = 1L;
}
