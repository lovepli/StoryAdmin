package com.story.storyadmin.domain.vo.pub;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 天气详情VO
 */
@Data
public class WeatherDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private Date date;
    /**
     * 星期
     */
    private String week;
    /**
     * 天气状况
     */
    private String wea;
    /**
     * 天气显示图片
     */
    private String weaImg;
    /**
     * 最高气温
     */
    private String tem1;
    /**
     * 最低气温
     */
    private String tem2;

}
