package com.story.storyadmin.service.oasys;


import com.github.pagehelper.PageInfo;
import com.story.storyadmin.domain.entity.oasys.NetFile;
import org.springframework.web.multipart.MultipartFile;

public interface NetFileService {
    PageInfo<NetFile> getNetFiles( int parentId, boolean personal, int pageNumber, int pageSize);

    void addFolder(String folderName,  int parentId, boolean personal);

    void uploadFile(MultipartFile multipartFile,  int parentId, boolean personal);

    void renameNetFile(int id, String newName);

    void deleteNetFiles(Integer[] ids);
}
