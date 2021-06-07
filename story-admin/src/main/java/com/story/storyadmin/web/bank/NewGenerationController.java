package com.story.storyadmin.web.bank;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import com.story.storyadmin.domain.entity.bank.NewGenerationRelease;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.bank.NewGenerationService;
import com.story.storyadmin.utils.bank.JsonObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank/NewGeneration")
public class NewGenerationController {

    @Autowired
    private NewGenerationService newGenerationService;

    @PostMapping("/findByPage")
    public Result findByPage(@RequestBody JSONObject jsonObject) throws IllegalAccessException {
        Page<NewGeneration> pageData = newGenerationService.findByPage(jsonObject);
        Result result = new Result();
        result.setData(pageData);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());

        // 对象拷贝1
        //String id="";
        //NewGeneration newGeneration=newGenerationService.getById(id); // 根据床查询
        //Map<String,Object> map= JsonObjectUtils.objectToMap(newGeneration); // 将对象转为map
        //// TODO 将map对象转对象，相同内容复制到newGenerationRelease中？？  JSONObject.parseObject()作用：json字符串转对象
        //NewGenerationRelease newGenerationRelease=JSONObject.parseObject(JSONObject.toJSONString(map),NewGenerationRelease.class);

        // 对象拷贝2
        //BeanUtils.copyProperties(); // TODO 对象拷贝的使用
        return result;
        // return Result.success("pageDate",pageData);

    }

    /**
     * 批量更新
     * @param NewGenerations 前端传递的参数也是一个json对象，只是是个对象数组
     * @return
     */
    public Result updateNewGeneration(@RequestBody List<NewGeneration> NewGenerations){
        newGenerationService.updateNewGeneration(NewGenerations);
         return new Result(true, "更新成功", ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }




}
