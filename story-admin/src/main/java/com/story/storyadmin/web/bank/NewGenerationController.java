package com.story.storyadmin.web.bank;


import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.bank.NewGenerationService;
import freemarker.core.ReturnInstruction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/NewGeneration")
public class NewGenerationController {

    //public Result findByPage(@RequestBody JSONObject jsonObject){
    //    Page<NewGeneration> pageData = NewGenerationService.findByPage(jsonObject);
    //    ReturnInstruction.Return Result.success("pageDate",pageData);
    //}




}
