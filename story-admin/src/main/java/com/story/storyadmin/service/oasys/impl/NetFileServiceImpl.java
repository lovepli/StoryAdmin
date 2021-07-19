package com.story.storyadmin.service.oasys.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.domain.entity.oasys.NetFile;
import com.story.storyadmin.mapper.oasys.NetFileMapper;
import com.story.storyadmin.service.oasys.NetFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class NetFileServiceImpl implements NetFileService {
    @Value("${oasys.upload.location}")
    private String location;

    private final NetFileMapper netFileMapper;

    public NetFileServiceImpl(NetFileMapper netFileMapper) {
        this.netFileMapper = netFileMapper;
    }

    @Override
    public PageInfo<NetFile> getNetFiles(  Long parentId, boolean personal, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        // 获取当前登录用户ID TODO 为啥不能获取到userId??fd
        Long userId= UserContext.getCurrentUser().getUserId();
        System.out.println("########用户ID："+userId);
        return new PageInfo<>(netFileMapper.selectNetFiles((long) 1,parentId, personal));
    }

    @Override
    public void addFolder(String folderName, Long parentId, boolean personal) {
        NetFile folder = new NetFile();
        folder.setName(folderName);
        folder.setPath("/");
        folder.setSize("-");
        folder.setType("文件夹");
       // folder.setUserId(UserContext.getCurrentUser().getUserId());
        folder.setUserId((long) 1);
        folder.setParentId(parentId);
        folder.setPersonal(personal);
        LocalDateTime time = LocalDateTime.now();
        folder.setCreatedTime(time);
        netFileMapper.insertNetFile(folder);
    }

    @Override
    public void uploadFile(MultipartFile multipartFile, Long parentId, boolean personal) {
        File folder = new File(location);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        long millis = System.currentTimeMillis();
        String fileName = multipartFile.getOriginalFilename();
        String path = location + millis + "-" + fileName;
        try {
            Files.write(Paths.get(path), multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetFile netFile = new NetFile();
        // 上传文件的路径
        netFile.setPath("/upload/file/" + millis + "-" + fileName);
        assert fileName != null;
        int index = fileName.lastIndexOf('.');
        netFile.setName(fileName.substring(0, index));
        netFile.setType(fileName.substring(index + 1));
        netFile.setSize(formatSize(multipartFile.getSize()));
        netFile.setParentId(parentId);
        netFile.setPersonal(personal);
        //netFile.setUserId(UserContext.getCurrentUser().getUserId());
        netFile.setUserId((long) 1);
        LocalDateTime time = LocalDateTime.now();
        netFile.setCreatedTime(time);
        netFileMapper.insertNetFile(netFile);
    }

    @Override
    public void renameNetFile(Long id, String newName) {
        netFileMapper.updateNetFile(id, newName);
    }

    @Override
    public void deleteNetFiles(Long[] ids) {
        for (Long id : ids) {
            NetFile netFile = netFileMapper.selectNetFile(id);
            if (!netFile.getType().equals("文件夹")) {
                String fileName = netFile.getPath().substring(13);
                File file = new File(location + fileName);
                file.delete();
            }
        }
        netFileMapper.deleteNetFiles(ids);
    }

    public String formatSize(long size) {
        if (size < 1024) {
            return size + "B";
        } else if (size < 1048576) {
            return (size >> 10) + "KB";
        } else {
            return (size >> 20) + "MB";
        }
    }
}
