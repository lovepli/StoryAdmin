package com.story.storyadmin.domain.vo.sysmgr;

import com.story.storyadmin.config.upload.entity.FileSlot;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author sunnj
 * @since 2019-07-12
 */
@Data
public class AttVo implements FileSlot {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String slotId;
    /**
     * 创建时间
     */
    private String fileCate;
    private String type;
    /**
     * 文件大小
     */
    private Long fileSize;
    private String originName;
    /**
     * 存储路径
     */
    private String path;
    /**
     * 描述
     */
    private String description;

    @Override
    public List<Att> getSlotFiles() {
        return null;
    }
}
