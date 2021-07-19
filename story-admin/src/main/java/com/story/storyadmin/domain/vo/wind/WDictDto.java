package com.story.storyadmin.domain.vo.wind;
import lombok.Data;

/**
 * 查询参数对象
 */
@Data
public class WDictDto {


    private String keyword;

    /**
     * 分组ID
     */
    private Long gid;

}
