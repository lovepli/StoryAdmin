package com.story.storyadmin.web.children;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.children.DScore;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.children.DScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 59688
 * @date: 2021/7/6
 * @description:
 */
@RestController
@RequestMapping("/children/score")
public class DScoreController extends ChildrenBaseController{

    @Autowired
    private DScoreService dScoreService;


    @PostMapping("/findByPage")
    public Result findByPage(@RequestBody JSONObject jsonObject) throws IllegalAccessException {
        Page<DScore> pageData = dScoreService.findByPage(jsonObject);
        Result result = new Result();
        result.setData(pageData);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
