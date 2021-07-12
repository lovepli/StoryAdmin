package com.story.storyadmin.web.sysmgr;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.common.exception.CustomException;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.AttService;
import com.story.storyadmin.utils.MethodUtil;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.io.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
public class AttController extends BaseController {

    @Value("${file.multipart.baseDir}")
    private String baseDir;

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
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
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
            result=new Result(attService.removeById(att.getId()),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
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
        // 上传附件方式1
        Att att= attService.save(request.getRequestURI(),file,"第一批次");
        return new Result(true,"上传成功",att,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    @ApiOperation(value = "附件管理" ,  notes="上传附件")
    @RequiresPermissions("sysmgr.att.upload")
    @RequestMapping(value="/upload2",method = {RequestMethod.POST})
    public Result upload2(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        int i=  attService.uploadFile(file);
        if(i<1){
            return new Result(true,"上传失败",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else {
            return new Result(true,"上传成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
    }

    @ApiOperation(value = "附件管理" ,  notes="导入附件Excel数据")
    @RequiresPermissions("sysmgr.att.upload")
    @RequestMapping(value="/importExcel",method = {RequestMethod.POST})
    public Result importExcel(@RequestParam("file") MultipartFile file) throws Exception {
       attService.importExcel(file);
       return new Result(true,"上传成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }



    @ApiOperation(value = "附件管理" ,  notes="下载附件")
    @RequiresPermissions("sysmgr.att.download")
    @RequestMapping(value = "/download/{id}", method = GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("id") Long fileId) {
        attService.downloadFile(response,fileId);
    }

    @ApiOperation(value = "附件管理" ,  notes="导出附件列表")
   // @RequiresPermissions("sysmgr.att.export")
    @RequiresPermissions("sysmgr.att.download")
    @RequestMapping(value="/export",method = {RequestMethod.POST})
    public Result export(@RequestBody JSONObject jsonObject,HttpServletResponse response) throws IOException {
        String fileName=jsonObject.getString("downLoadName");
        System.out.println("下载文件名"+fileName);
        return attService.export(jsonObject,response);
    }



    /**
     * 在线展示员工上传的pdf信息
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "附件管理" ,  notes="在线展示员工上传的pdf信息")
    @RequiresPermissions("sysmgr.att.query")
    @PostMapping("/findFilePdfDetail")
    public Result findFilePdfDetail(@RequestBody JSONObject jsonObject){
        String data = attService.findFileInfoDetail(jsonObject);
        //Map<String, Object> map = new HashMap<>();
        //map.put("result", data);
        return  new Result(true, "查询成功", data ,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


}
