package com.story.storyadmin.web.dictonecode;

import com.story.storyadmin.domain.entity.dictonecode.DictOneDO;
import com.story.storyadmin.service.dictonecode.DictOneService;
import com.story.storyadmin.utils.dictonecode.EntityListToExcelUtil;
import com.story.storyadmin.utils.dictonecode.JsonUtil;
import com.story.storyadmin.utils.dictonecode.R;
import com.story.storyadmin.utils.stringMethordUtil.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈https://gitee.com/hanxiaozhang2018/springboot_demo〉
 *
 * @author hanxinghua
 * @create 2020/2/24
 * @since 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/dictThreadTest")
public class DictOneController {

    @Autowired
    private DictOneService dictOneService;

    @GetMapping
    public String excelTest(){

        return "importExcel";

    }

    @ResponseBody
    @PostMapping("/importExcel")
    public R importExcel(@RequestParam(value = "file") MultipartFile file) {

        if (file == null) {
            return R.error(1, "文件不能为空");
        }

        if (StringUtil.isBlank(file.getOriginalFilename()) || file.getSize() == 0) {
            return R.error(1, "文件不能为空");
        }

        long startTime = System.currentTimeMillis();
        log.info("Excel开始导入,logId:[{}]", startTime);
        //数据导入处理
        R r = dictOneService.importExcel(file);

        if ("1".equals(r.get("code").toString())) {
            Map<String, Object> map = (Map) r.get("map");
            map.put("logId",startTime);
            log.info("Excel导入出错，logId:[{}]", startTime);
            return R.error(1, map, "导入时有错误信息");
        }
        long endTime = System.currentTimeMillis();
        log.info("Excel导入成功,logId:[{}],导入Excel耗时(ms):[{}]", startTime,endTime-startTime);
        return r;
    }



    @ResponseBody
    @PostMapping("/exportExcel")
    public void exportExcel(@RequestParam("data") String data, HttpServletResponse response) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        //将Json字符串转Map
        Map<String, Object> params = JsonUtil.jsonToMapSO(data);
        log.info("Excel导出错误信息，logId:[{}]", params.get("logId").toString());
        //response设置返回类型
        setDownloadExcelResponse(response, params.get("fileName").toString());
        //数据导出为excel
        EntityListToExcelUtil.getInstance().
                executeXLSX(JsonUtil.jsonToLinkedHashMapSS(params.get("title").toString()),
                        JsonUtil.jsonToList(params.get("errorData").toString(), DictOneDO.class),
                        response.getOutputStream());



    }

    /**
     * 设置下载文件响应信息
     *
     * @param response
     * @param fileName
     */
    private void setDownloadExcelResponse(HttpServletResponse response, String fileName) {

        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error("该文件[{}]不支持此编码转换,异常消息:[{}]",fileName,e.getMessage());
        }
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //使用Content-Disposition,一定要确保没有禁止浏览器缓存的操作
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 设计思想：
     * 1.Excel工具类：
     *       这块主要有两个关于Excel的工具类，一个将Excel转化成List集合的工具类，它的名字叫ExcelToEntityListUtil.java；另一个是将List集合转化为Excel的工具类，它的名字叫EntityListToExcelUtil.java。
     *       它两有一个共同的特点是：都是单例类，而且我在这里采用的静态内部类的单例，这样做的好处是既可以懒加载，又保证了线程安全，效率也比较高。这种静态内部类的单例模式比较推荐大家去使用。
     *
     * 2、多线程处理+多线程事务回滚模块：
     *        在实际的环境中，导入的Excel可能数据量很大，如果已经对代码进行批量插入、重复数据只查一次、代码逻辑等优化，还没有降低程序处理的时间，那我们就可以考虑使用多线程来处理数据。
     *        但是，多线程是一把双刃剑，使用得当则提高处理能力，降低程序运行时间；反之，会造成线程不安全，脏数据等致命问题。在单线程处理数据时，我们通常使用事务来避免脏数据，
     *        但是在多线程下事务可能就不好用了，原因是数据交给了多个线程去处理，事务只能控制一个线程内的数据，一个线程出错了，另一个线程的数据可能已经出入库了，破坏了数据的完整性。
     *
     *        如何在多线程下使事务回滚呢？
     *        这里需要用到同步锁、等待与通知、Volatile关键字等相关知识。原理是这样：我们创建一个多线程结束标识的类（MultiThreadEndFlag.java），
     *        这个类会有线程总个数、失败线程数据、是否全部成功等属性（它们都使用Volatile关键字，保证线程安全），每个线程执行完业务操作后都会调用此类等待结
     *        束的方法（synchronized void waitForEnd(int resultFlag)），说明自己已经完成业务，waitForEnd方法会调用wait()，使线程停止后面代码的运行，放弃CUP运行时间进入等待池。
     *        如果每个线程都运行完业务逻辑，代码会调用多线程标识类的结束方法（end()）,end方法会调用notifyAll()，通知所有线程继续运行，这时，每个线程会通过多线程标识类中是否全部成功属性去判断是否成功，
     *        如果不是成功，线程自己会自己抛出异常，使事务回滚。
     *
     *        上面讲了如何使多线程事务回滚，下面说一说如何使用多线程，我这里会用到Executor相关线程池的知识，如何不太熟悉可以自己去学习一下。此外，
     *        我这套方法还支持业务判断有问题数据导出并提示错误原因的功能，该功能主要是通过Callable创建线程，可以通过Future获取返回值的知识实现的
     */


}
