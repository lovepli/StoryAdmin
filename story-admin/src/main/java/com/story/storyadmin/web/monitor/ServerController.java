package com.story.storyadmin.web.monitor;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.monitorserver.Server;
import com.story.storyadmin.domain.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "服务监控")
@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @ApiOperation(value = "服务监控" ,  notes="查询服务监控信息")
    @RequiresPermissions("monitor.server.query")
    @RequestMapping(value="/info",method = {RequestMethod.POST,RequestMethod.GET})
    public Result info() throws Exception {
        Result result = new Result();
        Server server = new Server();
        server.copyTo();
        result.setData(server);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
