package com.story.storyadmin.mapper.sysmgr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.vo.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachmentMapper extends BaseMapper<Attachment> {
    /**
     * 按ID获取文件名称
     *
     * @param ids ID
     * @return ID-fileName
     */
    List<Attachment> selectNamesByIds(@Param("ids") List<String> ids);

    /**
     * 按编号获取文件信息
     *
     * @param sequence 文件编号
     * @return 文件信息
     */
    Attachment selectBySequence(@Param("sequence") String sequence);

}
