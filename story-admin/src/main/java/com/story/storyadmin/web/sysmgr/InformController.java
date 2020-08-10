package com.story.storyadmin.web.sysmgr;
import com.story.storyadmin.service.sysmgr.InformService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "系统通知")
@RestController
@RequestMapping("/sysmgr/inform")
public class InformController {

    @Autowired
    InformService informService;


}
