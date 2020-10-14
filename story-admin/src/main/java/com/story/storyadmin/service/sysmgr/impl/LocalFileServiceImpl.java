package com.story.storyadmin.service.sysmgr.impl;

import com.story.storyadmin.common.ApiException;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.helper.SequenceHelper;
import com.story.storyadmin.service.sysmgr.AttachmentService;
import com.story.storyadmin.service.sysmgr.IFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 将附件存在本地
 *
 * @author zhyyy
 **/
@Service
public class LocalFileServiceImpl implements IFileService {
    private final Logger LOG = LoggerFactory.getLogger(LocalFileServiceImpl.class);

    @Autowired(required=true)
    SequenceHelper sequenceHelper;
    @Autowired
    AttachmentService attachmentService;

    @Value("${upload-file.path}")
    private String fileRoot;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Result uploadFile(MultipartFile file, Long userId) {
        if (file.isEmpty()) {
            throw new ApiException("文件为空，请检查文件以及网络");
        }
        String filename = file.getOriginalFilename();
        // 生成流水号
        String sequence = sequenceHelper.generate(Attachment.class, "sequence");
        String realFileName = sequence + "_" + filename;
        File target = new File(fileRoot + File.separator + realFileName);
        try {
            file.transferTo(target);
            Attachment attachment = new Attachment();
            attachment.setFileName(filename);
            attachment.setFilePath(target.getPath());
            attachment.setRealFileName(realFileName);
            attachment.setSequence(sequence);
            attachment.setStatus(FILE_EXISTING_ON_DISK);
            attachment.setUploadDate(new Date());
            attachment.setUploader(userId);
            attachment.setFileSize(file.getSize());
            // 先保存，再返回ID信息
            return attachmentService.persist(attachment);
        } catch (IOException e) {
            LOG.error(String.valueOf(e));
            LOG.warn("文件处理失败：" + filename);
            throw new ApiException("文件处理失败");
        }
    }
    @Override
    public Result deleteFile(Long fileId, Long userId) {
        Attachment systemFile = attachmentService.selectAttachmentById(fileId);

        if (systemFile == null) {
            throw new ApiException("文件不存在");
        }
        if (FILE_DELETED_ON_DISK.equals(systemFile.getStatus())) {
            throw new ApiException("文件不已被删除");
        }
        systemFile.setDeleteDate(new Date());
        systemFile.setDeleter(userId);
        systemFile.setStatus(FILE_DELETED_ON_DISK);
        // 修改
        attachmentService.persist2(systemFile);

        File file = new File(systemFile.getRealFileName());
        if (file.exists()) {
            if (!file.delete()) {
                throw new ApiException("删除失败");
            }
        }
        LOG.info("删除文件成功：" + systemFile.getFilePath());
        return new Result(true, "删除文件成功", null, Constants.TOKEN_CHECK_SUCCESS);
    }

    @Override
    public Attachment getById(Long fileId) {
        return attachmentService.getById(fileId);
    }
}
