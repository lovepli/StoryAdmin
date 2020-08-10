package com.story.storyadmin.mapper.sysmgr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachmentMapper extends BaseMapper<Attachment> {
    List<Attachment> selectNamesByIds(@Param("ids") List<String> ids);
}
