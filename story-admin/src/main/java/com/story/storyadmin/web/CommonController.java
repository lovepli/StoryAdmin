package com.story.storyadmin.web;

import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.ImageFileService;
import com.story.storyadmin.utils.ruoyiutils.StringUtils;
import com.story.storyadmin.utils.ruoyiutils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用请求处理
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController{


    @Value("${file.multipart.baseDir}")
    private String baseDir;

    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    @Autowired
    ImageFileService imageFileService;

    /**
     * 图片访问的URL地址
     */
    private String  url;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        logger.info("文件名称：{}",fileName);
        logger.info("是否删除：{}",delete);
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            // 下载文件存放的地方
            String filePath = baseDir + "download/"+ fileName;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            logger.error("下载文件失败", e);
        }
    }


    /**
     * 上传用户图像
     * @param file
     * @return
     */
    @RequestMapping(value="/uploadImageFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file) throws IOException{

        Result result ;
        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            result = new Result(false, "上传文件不可为空", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }


        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");


        //加个时间戳，尽量避免文件名称重复
        String path = mImagesPath +fileName;
        //String path = "D:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            result = new Result(false, "文件已经存在", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            url="http://localhost:9430/images/"+fileName;
            // 保存文件名，路径，访问URL
            int jieguo= imageFileService.insertUrl(fileName,path,url);
            System.out.print("插入结果"+jieguo+"\n");
            System.out.print("保存的完整url===="+url+"\n");
        } catch (IOException e) { //受检查异常，不是throw Eexception()这样处理
            logger.error("上传失败", e);
            result = new Result(false, "上传失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        System.out.println("上传成功,文件url=="+url);
        result = new Result(true, "上传成功", url ,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

}
