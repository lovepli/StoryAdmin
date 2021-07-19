package com.story.storyadmin.service.oasys;


import com.github.pagehelper.PageInfo;
import com.story.storyadmin.domain.entity.oasys.NetFile;
import org.springframework.web.multipart.MultipartFile;

public interface NetFileService {
    PageInfo<NetFile> getNetFiles(Long parentId, boolean personal, int pageNumber, int pageSize);

    void addFolder(String folderName, Long parentId, boolean personal);

    void uploadFile(MultipartFile multipartFile,  Long parentId, boolean personal);

    void renameNetFile(Long id, String newName);

    void deleteNetFiles(Long[] ids);
}
