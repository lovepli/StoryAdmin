package com.story.storyadmin.web.oaSys;


import com.github.pagehelper.PageInfo;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.oasys.NetFile;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.oasys.NetFileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class NetFileController {

    private final NetFileService netFileService;

    public NetFileController(NetFileService netFileService) {
        this.netFileService = netFileService;
    }

    /**
     * 查询文件列表
     * @param parentId
     * @param personal
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/oasys/file/getFiles",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.file.query")
    public Result getNetFiles( @RequestParam("parentId") Long parentId,
                               @RequestParam("personal") boolean personal,
                               @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Result result = new Result();
        PageInfo<NetFile> pageInfo = netFileService.getNetFiles( parentId, personal, pageNumber, pageSize);
        result.setData(pageInfo);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 新增文件
     * @param parentId
     * @param folderName
     * @param personal
     * @return
     */
    @RequestMapping(value="/oasys/file/addFolder",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.file.save")
    public Result addFolder(@RequestParam("parentId") Long parentId,
                            @RequestParam("folderName") String folderName,
                            @RequestParam("personal") boolean personal) {
        Result result ;
        netFileService.addFolder(folderName, parentId, personal);
        result=new Result(true,"添加成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 上传文件
     * @param parentId
     * @param personal
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/common/uploadFile",method = {RequestMethod.POST})
    public Result uploadFile(@RequestParam("parentId") Long parentId,
                                  @RequestParam("personal") boolean personal,
                                  @RequestParam("file") MultipartFile multipartFile) throws IOException {
        Result result ;
        netFileService.uploadFile(multipartFile,parentId, personal);
        result=new Result(true,"上传成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 上传文件
     * @param id
     * @param newName
     * @return
     */
    @RequestMapping(value="/oasys/file/renameFile",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.file.save")
    public Result renameNetFile(@RequestParam("id") Long id,
                                @RequestParam("newName") String newName) {
        Result result ;
        netFileService.renameNetFile(id, newName);
        result=new Result(true,"重命名成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    /**
     * 删除文件
     * @param ids
     * @return
     */
    @RequestMapping(value="/oasys/file/deleteFiles",method = {RequestMethod.POST})
    @RequiresPermissions("oasys.file.delete")
    public Result deleteNetFiles(@RequestBody Long[] ids) {
        Result result ;
        netFileService.deleteNetFiles(ids);
        result=new Result(true,"成功删除文件!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
