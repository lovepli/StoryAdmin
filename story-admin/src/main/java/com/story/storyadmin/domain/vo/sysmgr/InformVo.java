package com.story.storyadmin.domain.vo.sysmgr;



import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.entity.sysmgr.Inform;

import java.util.List;

/**
 * @author zhyyy
 **/
public class InformVo extends Inform {

    /**
     * 添加公告时，带附件ID
     */
    private List<Long> attachments;

    /**
     * 查找公告时，带附件信息
     */
    private List<Attachment> attachmentsToShow;

    /**
     * 查找公告时，带创建人ID
     */
    private String creatorName;

    public List<Long> getAttachments() {
        return attachments;
    }

    public InformVo setAttachments(List<Long> attachments) {
        this.attachments = attachments;
        return this;
    }

    public List<Attachment> getAttachmentsToShow() {
        return attachmentsToShow;
    }

    public InformVo setAttachmentsToShow(List<Attachment> attachmentsToShow) {
        this.attachmentsToShow = attachmentsToShow;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public InformVo setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }
}
