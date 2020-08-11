package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachmentService extends IService<Attachment> {

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

    /**
     * 保存
     * @param attachment
     * @return
     */
    Result persist(Attachment attachment,String sequence);

    /**
     * 修改
     * @param attachment
     * @return
     */
    void persist2(Attachment attachment);

    /**
     * 根据用户ID查查询
     * @param id
     * @return
     */
    Attachment selectAttachmentById(Long id);

}
