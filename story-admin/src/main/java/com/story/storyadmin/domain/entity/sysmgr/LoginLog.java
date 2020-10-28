package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.common.annotation.Excel;
import com.story.storyadmin.domain.entity.BaseEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author sunnj
 * @since 2019-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_login_log")
public class LoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String account;

    /**
     * 访问时间
     */
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 有效标志
     */
//    @Excel(name = "有效标志", readConverterExp = "0=无效,1=有效")
//    private String ynFlag;

}
