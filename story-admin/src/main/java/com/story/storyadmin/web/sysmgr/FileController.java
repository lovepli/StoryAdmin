package com.story.storyadmin.web.sysmgr;

import com.story.storyadmin.common.exception.CustomException;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.config.upload.annotation.FileSlotDisabled;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.IFileService;
import com.story.storyadmin.utils.MethodUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(description = "系统公告附件")
@RestController
@RequestMapping("/sysmgr/file")
public class FileController  {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    IFileService fileService;


    /**
     * 上传文件
     * @param file  文件
     * @return response
     */
    //@SysLogAnnotation  // TODO aop日志记录中不能解析文件参数，会导致fastJson解析异常
    @ApiOperation(value = "系统公告附件" ,  notes="上传文件")
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
    @ApiOperation(value = "系统公告附件" ,  notes="删除文件")
    @RequiresPermissions("sysmgr.file.delete")
    @RequestMapping(value = "/{id}", method = DELETE)
    public Result delete(@PathVariable("id") Long fileId) {
        return fileService.deleteFile(fileId, UserContext.getCurrentUser().getUserId());
    }

    /**
     * 下载文件
     * 使用axios下载附件 https://www.sundayfine.com/axios_qa/
     * 后端主要的转换为流写入
     * @param response 下载人token
     * @param fileId   文件ID
     */
    //@SysLogAnnotation
    @ApiOperation(value = "系统公告附件" ,  notes="下载文件")
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
        logger.info("文件路径:{}",filePath);
        logger.info("文件名:{}",fileName);
        File file = new File(filePath);
        if (file.exists()) {
            // 下载逻辑:
            // 强制设置下载而不是打开
            response.setContentType("application/force-download");
            // 设置文件名，fileName是下载的文件名
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
               // throw new CustomException("下载失败");
                throw new CustomException(ResultEnum.UNKNOWN_EXCEPTION.getCode(), "下载失败", MethodUtil.getLineInfo());
            }
        } else {
            response.setStatus(404);
        }
    }
}

/**
 * 文件上传下载
 * https://blog.csdn.net/caidewei121/article/details/107646525
 */
