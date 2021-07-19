package com.story.storyadmin.web.thordwaycontroller;

import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.utils.netMethordUtil.HttpClientUtil;
import com.story.storyadmin.utils.objectMethordUtil.JsonUtils;
import com.story.storyadmin.web.BaseController;

/**
 * @author: lipan
 * @date: 11:08 下午
 * @description: Java调用第三方接口示范 https://mp.weixin.qq.com/s/HWAYGSlpkLexyULTmcOHyg
 */
public class ThrodWayController extends BaseController {

    /**
     * 聚合接口校验身份证
     *
     * @param idCard
     * @param realName
     * @return boolean
     */
    public boolean identityCheck(String idCard, String realName) {
        logger.info("-----------------调用聚合数据 身份证验证API BEGIN--------------->");
        String key = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        String url = "http://op.juhe.cn/idcard/query" + "?key=" + key + "&idcard=" + idCard + "&realname=" + realName;
        logger.info("请求url:" + url);
        boolean match = false; //是否匹配
        try {
            String result = HttpClientUtil.doGet(url);
            System.out.println("请求结果：" + result);
            Result identityCheckResult = JsonUtils.parse(result, Result.class);
            //IdentityCheck identityCheck = JsonUtils.parse(result, "result", IdentityCheck.class);
            //logger.info(String.valueOf(identityCheckResult));
            //logger.info(identityCheck.toString());
            //if (identityCheckResult.correct() && identityCheck.getRes() == 1) {
            //    match = true;
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("<-----------------调用聚合数据 身份证验证API END---------------");
        return match;

    }
}
