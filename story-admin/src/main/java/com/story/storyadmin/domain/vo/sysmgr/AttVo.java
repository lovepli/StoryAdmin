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
    /**
     * 文件名
     */
    private String name;
    /**
     * 批次
     */
    private String slotId;
    /**
     * 类别
     */
    private String type;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 源文件名
     */
    private String originName;
    /**
     * 存储路径
     */
    private String path;
    /**
     * 描述
     */
    private String description;
    /**
     * 操作时间
     */
    private String fileCate;

    /**
     * 同一批关联文件 TODO 这里没有开发完
     * @return
     */
    @Override
    public List<Att> getSlotFiles() {
        return null;
    }
}
