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
@RequestMapping("/oasys/file")
public class NetFileController {

    private final NetFileService netFileService;

    public NetFileController(NetFileService netFileService) {
        this.netFileService = netFileService;
    }

    @RequestMapping(value="/getFiles",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.file.query")
    public Result getNetFiles( @RequestParam("parentId") int parentId,
                                                    @RequestParam("personal") boolean personal,
                                                    @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Result result = new Result();
        PageInfo<NetFile> pageInfo = netFileService.getNetFiles( parentId, personal, pageNumber, pageSize);
        result.setData(pageInfo);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        result.setMessage("获取成功！");
        return result;
    }

    @RequestMapping(value="/addFolder",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.file.save")
    public Result addFolder(@RequestParam("parentId") int parentId,
                            @RequestParam("folderName") String folderName,
                            @RequestParam("personal") boolean personal) {
        Result result ;
        netFileService.addFolder(folderName, parentId, personal);
        result=new Result(true,"添加成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequestMapping(value="/uploadFile",method = {RequestMethod.POST})
    @RequiresPermissions("oasys.file.upload")
    public Result uploadFile(@RequestParam("parentId") int parentId,
                                  @RequestParam("personal") boolean personal,
                                  @RequestParam("file") MultipartFile multipartFile) throws IOException {
        Result result ;
        netFileService.uploadFile(multipartFile, parentId, personal);
        result=new Result(true,"上传成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequestMapping(value="/renameFile",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.file.save")
    public Result renameNetFile(@RequestParam("id") int id,
                                     @RequestParam("newName") String newName) {
        Result result ;
        netFileService.renameNetFile(id, newName);
        result=new Result(true,"重命名成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    @RequestMapping(value="/deleteFiles",method = {RequestMethod.POST})
    @RequiresPermissions("oasys.file.delete")
    public Result deleteNetFiles(@RequestBody Integer[] ids) {
        Result result ;
        netFileService.deleteNetFiles(ids);
        result=new Result(true,"重命名成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
