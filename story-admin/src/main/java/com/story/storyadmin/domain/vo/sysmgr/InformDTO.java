package com.story.storyadmin.domain.vo.sysmgr;

import com.story.storyadmin.domain.entity.sysmgr.Inform;
import lombok.Data;
import java.util.Date;

/**
 * 查询帮助类
 */
@Data
public class InformDTO extends Inform {

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date startDate;

    private Date endDate;

    /**
     * 状态 0撤销 1正常或过期
     */
    private Short status;

    /**
     * 创建者id
     */
    private Long creator;


    @Override
    public String toString() {
        return "InformDTO{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", creator=" + creator +
                '}';
    }
}
