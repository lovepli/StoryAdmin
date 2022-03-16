package com.story.storyadmin.web;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.utils.JedisUtils;
import com.story.storyadmin.utils.ruoyiutils.sign.Base64;
import com.story.storyadmin.utils.ruoyiutils.uuid.IdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@Api(description = "验证码接口")
@Controller
public class CaptchaController extends BaseController{

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    @Autowired
    private JedisUtils jedisUtils;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /** 验证码类型 **/
    @Value("${project.captchaType}")
    private String captchaType;

    /**
     * 生成验证码
     */
    @ApiOperation(value = "验证码接口" ,  notes="生成验证码")
    @ResponseBody
    @RequestMapping(value = "/captchaImage", method = {RequestMethod.GET})
    public Result getCode() {

        Result result = new Result();

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        // 将验证码存入缓存中，并设置验证码过期时间 TODO 这里给验证码设置过期时间会出现登录不进去
        // jedisUtils.saveString(verifyKey, code, CAPTCHA_EXPIRATION);
        jedisUtils.saveString(verifyKey, code);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            result.setResult(false);
            result.setMessage(e.getMessage());
            //io异常
            result.setCode(ResultEnum.SERVER_ERROR.getCode());
            return result;
        }

        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        JSONObject json = new JSONObject();
        json.put("uuid", uuid);
        json.put("img", Base64.encode(os.toByteArray()));
        result.setData(json);
        // logger.info("传递给前端的验证码数据为:{}", result);
        return result;
    }
}
