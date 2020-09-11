package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.common.SrotyAdminOutException;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.AttVo;
import com.story.storyadmin.service.sysmgr.AttService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-07-02
 */
@Api(description = "附件管理")
@RestController
@RequestMapping("/sysmgr/att")
public class AttController {

    @Autowired
    AttService attService;

    /**
     * 分页查询
     * @param att
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "附件管理" ,  notes="查询附件列表")
    @RequiresPermissions("sysmgr.att.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Att att,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<Att> page = new Page(pageNo, limit);
        QueryWrapper<Att> eWrapper = new QueryWrapper(att);
        eWrapper.eq("yn_flag","1");
        eWrapper.orderByDesc("created_time");
        IPage<Att> list = attService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 删除
     * @param att
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "附件管理" ,  notes="删除附件信息")
    @RequiresPermissions("sysmgr.att.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Att att){
        Result result ;
        if(att.getId()!=null){
            result=new Result(attService.removeById(att.getId()),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }

    /**
     * 上传附件
     * @param
     * @return
     * 添加日志注解，会出现fastJson解析异常报错
     */
    //@SysLogAnnotation
    @ApiOperation(value = "附件管理" ,  notes="上传附件")
    @RequiresPermissions("sysmgr.att.upload")
    @RequestMapping(value="/upload",method = {RequestMethod.POST})
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        Att att= attService.save(request.getRequestURI(),file,"第一批次");

        Result result = new Result(true,"上传成功",att,Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    //@SysLogAnnotation
    @ApiOperation(value = "附件管理" ,  notes="下载附件")
    @RequiresPermissions("sysmgr.att.download")
    @RequestMapping(value = "/download/{id}", method = GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("id") Long fileId) throws UnsupportedEncodingException {
        Att att = attService.getById(fileId);
        if (att == null) {
            response.setStatus(404);
            return;
        }
        String filePath = att.getPath();
        String fileName = att.getName();
        File file = new File(filePath);
        String newFileName = att.getName();
        if (file.exists()) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(newFileName, "UTF-8"));
            response.setHeader("Content-Length", String.valueOf(att.getFileSize()));
            //将允许浏览器访问的头放入白名单
            response.setHeader("Access-Control-Expose-Headers", "FileName");
            //FileName 为自定义头
            response.setHeader("FileName", URLEncoder.encode(newFileName, "UTF-8"));

            // 强制设置下载而不是打开
//            response.setContentType("application/force-download");
//            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
//            byte[] buffer = new byte[1024];
//            try (
//                    FileInputStream fis = new FileInputStream(file);
//                    BufferedInputStream bis = new BufferedInputStream(fis)
//            ) {
//                OutputStream outputStream = response.getOutputStream();
//                int i = bis.read(buffer);
//                while (i != -1) {
//                    outputStream.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//            } catch (Exception e) {
//                response.setStatus(404);
//                throw new SrotyAdminOutException("下载失败");
//            }
//        } else {
//            response.setStatus(404);
       }
    }
}
