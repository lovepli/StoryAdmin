package com.story.storyadmin.service.sysmgr;

import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统附件管理服务，默认将附件存储本地（服务器或nas）
 * 可使用云对象存储进行扩展
 *
 * @author zhyyy
 * @see
 **/
public interface IFileService {
    /**
     * 状态：正常
     */
    Short FILE_EXISTING_ON_DISK = 1;

    /**
     * 状态：已删除
     */
    Short FILE_DELETED_ON_DISK = 2;

    /**
     * 上传文件
     *
     * @param file 文件
     * @param userId 上传者ID
     * @return 文件ID
     */
    Result uploadFile(MultipartFile file, Long userId);

    /**
     * 删除文件
     *
     * @param fileId 文件ID
     * @param userId   删除者ID
     */
    Result deleteFile(Long fileId, Long userId);

    /**
     * 按主键获取
     *
     * @param fileId 文件id
     * @return 文件信息
     */
    Attachment getById(Long fileId);
}
