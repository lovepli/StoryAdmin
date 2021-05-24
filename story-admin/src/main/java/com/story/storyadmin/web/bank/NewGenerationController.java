package com.story.storyadmin.web.bank;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.bank.NewGenerationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/NewGeneration")
public class NewGenerationController {

    @Autowired
    private NewGenerationService newGenerationService;

    public Result findByPage(@RequestBody JSONObject jsonObject){
        Page<NewGeneration> pageData = newGenerationService.findByPage(jsonObject);
        Result result = new Result();
        result.setData(pageData);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());

        //BeanUtils.copyProperties(); // TODO 对象拷贝的使用
        return result;
        // return Result.success("pageDate",pageData);

    }




}
