package com.story.storyadmin.web.bank;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.bank.NewGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
        //NewGenerationRelease newGeneration=JSONObject.parseObject(JSONObject.toJSONString(map),NewGenerationRelease.class);

        // 对象拷贝2
        //BeanUtils.copyProperties(newGeneration,newGeneration); // TODO 对象拷贝的使用
        return result;
        // return Result.success("pageDate",pageData);

    }

    /**
     * 批量更新
     *
     * @param NewGenerations 前端传递的参数也是一个json对象，只是是个对象数组
     * @return
     */
    @RequestMapping("/updateNewGeneration")
    public Result updateNewGeneration(@RequestBody List<NewGeneration> NewGenerations) {
        newGenerationService.updateNewGeneration(NewGenerations);
        return new Result(true, "更新成功", ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody JSONObject jsonObject){
        // 将前端传过来的json数据转化为JavaBean对象,然后给对象赋值
        NewGeneration newGeneration = JSONObject.toJavaObject(jsonObject,NewGeneration.class);
        return new Result(true, "更新成功", ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 查询所有orgName，并以label-value下拉框结构展示
     * @return
     */
    @RequestMapping("/findAllOrgName")
    public Result findAllOrgName(){
        QueryWrapper<NewGeneration> queryWrapper=new QueryWrapper<>();
        //queryWrapper.eq("del_flag","1");
        List<NewGeneration> userList = newGenerationService.list(queryWrapper);
        // userList.forEach(System.out::println);
        List<Map<String,String>> list=new ArrayList<>();
        getNewGenerationItemNameList(userList,list);
        return new Result(true, "成功",list, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }



    /**
     * 这个方法最好写成一个泛型方法，方便扩展
     * @param itemList
     * @param list
     */
    private void getNewGenerationItemNameList(List<NewGeneration> itemList,List<Map<String,String>> list){
        for (NewGeneration user:itemList){
            Map<String,String> map=new HashMap<>();
            map.put("label",user.getOrgName());
            map.put("value", user.getId());
            list.add(map);
        }
    }



}
