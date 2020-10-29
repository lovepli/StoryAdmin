package com.story.storyadmin.web.tool;


import com.story.storyadmin.utils.ruoyiutils.StringUtils;
import com.story.storyadmin.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * swagger 接口
 * 
 * @author
 */
@Controller
@RequestMapping("/document/swagger")
public class SwaggerController extends BaseController {

    @RequestMapping(value="/info",method = {RequestMethod.POST,RequestMethod.GET})
    @RequiresPermissions("document.swagger.query")
    public String index()
    {
        return redirect("/swagger-ui.html");
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }
}
