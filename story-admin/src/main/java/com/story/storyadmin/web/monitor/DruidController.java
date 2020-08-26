package com.story.storyadmin.web.monitor;

import com.story.storyadmin.utils.ruoyiutils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 数据监控
 *
 */
@Api(description = "数据监控")
@Controller
@RequestMapping("/monitor/druid")
public class DruidController {

    @ApiOperation(value = "数据监控" ,  notes="查询数据监控信息")
    @RequestMapping(value="/info",method = {RequestMethod.POST,RequestMethod.GET})
    @RequiresPermissions("monitor.druid.query")
    public String index()
    {
        return redirect("/druid/index.html");
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }
}
