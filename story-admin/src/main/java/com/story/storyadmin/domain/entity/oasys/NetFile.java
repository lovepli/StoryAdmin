package com.story.storyadmin.domain.entity.oasys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: lipan
 * @date: 2020/11/26
 * @description:
 */
@Data
@Alias("NetFile")
public class NetFile implements Serializable {
    private Long id;
    private String name;
    private String path;
    private String type;
    private String size;
    private Long userId;
    private Long parentId;
    /**
     * 是否公共网盘
     */
    private boolean personal;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdTime;
    private static final long serialVersionUID = 1L;
}
