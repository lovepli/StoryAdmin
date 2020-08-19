package com.story.storyadmin.web.sysmgr;

import com.story.storyadmin.common.SrotyAdminOutException;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.config.upload.annotation.FileSlotDisabled;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 附件Controller
 *
 * @author zhyyy
 **/
@Api(description = "附件")
@RestController
@RequestMapping("/sysmgr/file")
public class FileController  {

    @Autowired
    IFileService fileService;


    /**
     * 上传文件
     * @param file  文件
     * @return response
     */
    @SysLogAnnotation
    @ApiOperation(value = "附件" ,  notes="上传文件")
    @RequiresPermissions("sysmgr.file.upload")
    @RequestMapping(value = "/upload", method = POST)
    @FileSlotDisabled
    public Result upload(@RequestParam("file") MultipartFile file) {
        // TODO 这里的ID值取不到？
        Long id= UserContext.getCurrentUser().getUserId();
        System.out.println("################上传者的id:"+id);
        Long defaultId=1L; //给一个默认的id
        return fileService.uploadFile(file, defaultId);
    }

    /**
     * 删除文件
     *
     * @param fileId 文件ID
     *
     * @return response
     */
    @SysLogAnnotation
    @ApiOperation(value = "附件" ,  notes="删除文件")
    @RequiresPermissions("sysmgr.file.delete")
    @RequestMapping(value = "/{id}", method = DELETE)
    public Result delete(@PathVariable("id") Long fileId) {
        return fileService.deleteFile(fileId, UserContext.getCurrentUser().getUserId());
    }

    /**
     * 下载文件
     *
     * @param response 下载人token
     * @param fileId   文件ID
     */
    @SysLogAnnotation
    @ApiOperation(value = "附件" ,  notes="下载文件")
    @RequiresPermissions("sysmgr.file.download")
    @RequestMapping(value = "/download/{id}", method = GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("id") Long fileId) {
        Attachment attachment = fileService.getById(fileId);
        if (attachment == null) {
            response.setStatus(404);
            return;
        }
        String filePath = attachment.getFilePath();
        String fileName = attachment.getSequence();
        File file = new File(filePath);
        if (file.exists()) {
            // 强制设置下载而不是打开
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            try (
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis)
            ) {
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                response.setStatus(404);
                throw new SrotyAdminOutException("下载失败");
            }
        } else {
            response.setStatus(404);
        }
    }
}