package com.story.storyadmin.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.config.props.WeatherProperties;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.pub.WeatherDetailVo;
import com.story.storyadmin.domain.vo.pub.WeatherVo;
import com.story.storyadmin.utils.HttpClientUtils;
import com.story.storyadmin.utils.JedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有用户都可以访问的首页公共接口
 */
@Api(description = "首页天气接口")
@RestController
public class PubController extends BaseController{

    @Autowired
    WeatherProperties weatherProperties;

    @Autowired
    JedisUtils jedisUtils;

    /**
     * 首页的天气接口 调用第三方API接口，并对返回参数进行处理
     * @return
     */
    @ApiOperation(value = "查询天气" ,  notes="查询天气信息")
    @RequestMapping(value="/weather",method = {RequestMethod.GET})
    public Result weather(){

        WeatherVo weatherVo;
        //城市id编号， 如北京为101010100，深圳为 101280601  TODO 这里可以做一个搜索查询，根据不同的城市查询天气状况
        String strCityId= "101280601";
        String weatherCacheKey= "weather_" + strCityId;
        if(jedisUtils.exists(weatherCacheKey)){
            weatherVo = (WeatherVo)jedisUtils.getObject(weatherCacheKey);
        }else{
            Map<String,String> params= new HashMap<>();
            params.put("appid",weatherProperties.getAppId());
            params.put("appsecret",weatherProperties.getAppSecret());
            //固定值v1
            params.put("version","v1");
            params.put("cityid",strCityId);
            //http调用第三方api接口 参考文档https://www.tianqiapi.com/index/doc
            String httpResult = HttpClientUtils.get("https://www.tianqiapi.com/api/",params);

            weatherVo= new WeatherVo();
            WeatherDetailVo weatherEntity;
            List<WeatherDetailVo> weatherDetailList= new ArrayList<>();

            //处理接口返回的json字符串数据
            JSONObject response=JSONObject.parseObject(httpResult);
            JSONArray data=response.getJSONArray("data");

            Long cityId=response.getLong("cityid");
            String province=response.getString("province");
            String city=response.getString("city");

            JSONObject tempObject;
            for(Object object:data){
                weatherEntity = new WeatherDetailVo();
                tempObject = ((JSONObject)object);
                weatherEntity.setDate(tempObject.getDate("date"));
                weatherEntity.setWeek(tempObject.getString("week"));
                weatherEntity.setWea(tempObject.getString("wea"));
                weatherEntity.setWeaImg(tempObject.getString("wea_img"));
                weatherEntity.setTem1(tempObject.getString("tem1"));
                weatherEntity.setTem2(tempObject.getString("tem2"));
                // 一周天气详情列表
                weatherDetailList.add(weatherEntity);
            }
            weatherVo.setCityId(cityId);
            weatherVo.setProvince(province);
            weatherVo.setCity(city);
            weatherVo.setDateList(weatherDetailList);

            if (logger.isInfoEnabled()) {
                logger.info("天气详情：{}",weatherVo.toString());
            }

            //缓存  将首页查询出来的天气信息，存入到redis中进行缓存并设置失效时间为3个小时，就不需要每次回到首页都调用一次接口进行查询,这样查询的效率会大大提高
            jedisUtils.saveObject(weatherCacheKey, weatherVo, Constants.ExpireTime.THREE_HOURS);
        }
        Result result = new Result(true,weatherVo);
        return result;
    }
}
