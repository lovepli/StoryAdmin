package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachmentService extends IService<Attachment> {

    List<Attachment> selectNamesByIds(@Param("ids") List<String> ids);
}
