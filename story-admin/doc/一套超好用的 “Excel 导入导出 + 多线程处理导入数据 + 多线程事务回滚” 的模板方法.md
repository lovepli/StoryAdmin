> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/huantai3334/article/details/104611292/?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.control&spm=1001.2101.3001.4242)

一、模板流程：
-------

![](https://img-blog.csdnimg.cn/20200302093550134.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2h1YW50YWkzMzM0,size_16,color_FFFFFF,t_70)

 二、功能演示：
--------

### 1.Excel 数据：

数据说明：第一条数据完整，可以成功导入；第二条数据无姓名，业务逻辑姓名不允许为空，会导出到错误 Excel 中；第三条数据无姓名无类型，业务逻辑姓名类型不能为空，会导出错误 Excel 中。

![](https://img-blog.csdnimg.cn/20200302150906764.png)

### 2. 导入页面：

选择相关 Excel，点击导出测试按钮：

![](https://img-blog.csdnimg.cn/20200302150951515.png)

### 3. 正确数据入库：

![](https://img-blog.csdnimg.cn/20200302151705290.png)

### 4. 错误数据导出成 Excel 并有提示： 

![](https://img-blog.csdnimg.cn/20200302151850189.png)

三、主要源代码： 
---------

### 1.Excel 工具类：

这块主要有两个关于 Excel 的工具类，一个将 Excel 转化成 List 集合的工具类，它的名字叫 ExcelToEntityListUtil.java；另一个是将 List 集合转化为 Excel 的工具类，它的名字叫 EntityListToExcelUtil.java。它两有一个共同的特点是：都是单例类，而且我在这里采用的静态内部类的单例，这样做的好处是既可以懒加载，又保证了线程安全，效率也比较高。这种静态内部类的单例模式比较推荐大家去使用。相关代码如下：

ExcelToEntityListUtil.java：

```
package com.hanxiaozhang.utils;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 * 功能描述: <br>
 * 〈Excel导入成实体集合〉
 *
 * @Author:hanxinghua
 * @Date: 2020/2/24
 */
@Slf4j
public class ExcelToEntityListUtil {
 
 
    private BeanStorage storage = new BeanStorage();
 
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
    private ExcelToEntityListUtil(){}
 
    private static class Holder{
        private static  ExcelToEntityListUtil INSTANCE=new ExcelToEntityListUtil();
    }
 
    public static ExcelToEntityListUtil getInstance(){
        return Holder.INSTANCE;
    }
 
    /**
     * 执行Excel转EntityList
     *
     * @param entity
     * @param excel  excel输入流
     * @param titleToAttr  key为excel的中文title，value为该中文title对于的entity属性名
     * @param <T>
     * @return
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvalidFormatException
     */
    public <T> ArrayList<T> execute(Class<?> entity, InputStream excel, Map<String, String> titleToAttr) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,InvalidFormatException {
        ArrayList<T> result = new ArrayList<T>();
 
        Workbook book = create(excel);
        Sheet sheet = book.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        if (rowCount < 1) {
            return result;
        }
        //加载标题栏数据,以此和headMapping对应
        Map<Integer, String> headTitle = loadHeadTitle(sheet);
        //循环行
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            //空行跳过
            if (row == null) {
                continue;
            }
            int cellCount = row.getLastCellNum();
            @SuppressWarnings("unchecked")
            T instance = (T) entity.newInstance();
            int col = 0;
            try {
                //循环每行单元格
                for (; col < cellCount; col++) {
                    String cellValue = getCellValue(row.getCell(col));
                    if (null != cellValue) {
                        this.setEntity(entity, instance, titleToAttr.get(headTitle.get(col)), cellValue);
                    }
                }
                result.add(instance);
            } catch (Exception e) {
                String message="第" + (i + 1) + "行，" + headTitle.get(col) + "字段，数据错误!";
                log.info(message);
                throw new IllegalArgumentException(message);
            }
        }
        excel.close();
        return result;
    }
 
    /**
     * 加载Excel的标题栏
     *
     * @param sheet
     * @return 返回列序号和对于的标题名称Map
     */
    private Map<Integer, String> loadHeadTitle(Sheet sheet) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        Row row = sheet.getRow(0);
        int cellCount = row.getLastCellNum();
        for (int i = 0; i < cellCount; i++) {
            String value = row.getCell(i).getStringCellValue();
            if (null == value) {
                throw new RuntimeException("Excel导入：标题栏不能为空！");
            }
            map.put(i, value);
        }
        return map;
    }
 
    /**
     * 获取表格列的值
     *
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        if (null == cell||"".equals(cell)) {
            return "";
        }
        String value = "";
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_BLANK:
                //空值
                value = null;
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                // 判断当前的cell是否为Date
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = dateFormat.format(cell.getDateCellValue());
                } else {
                    value = String.valueOf((long) cell.getNumericCellValue());
                }
                break;
            case XSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                log.info("不支持带有函数的单元格!");
                throw new IllegalArgumentException("不支持带有函数格式的单元格!");
            default:
                log.info("单元格格式有误!");
                throw new IllegalArgumentException("单元格格式有误!");
        }
 
        return value;
    }
 
 
    /**
     * 封装实体值
     *
     * @param clazz
     * @param instance
     * @param pro
     * @param value
     * @param <T>
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws Exception
     */
    private <T> void setEntity(Class<?> clazz, T instance, String pro, String value) throws SecurityException, NoSuchMethodException, Exception {
        String innerPro = null;
        String outterPro = null;
        if (pro.contains(".")) {
            String[] pros = pro.split("\\.");
            outterPro = pros[0];
            innerPro = pros[1];
            // 将成员变量的类型存储到仓库中
            storage.storeClass(instance.hashCode() + outterPro, clazz.getDeclaredMethod(this.initGetMethod(outterPro), null).getReturnType());
        }
        String getMethod = this.initGetMethod(outterPro != null ? outterPro : pro);
        Class<?> type = clazz.getDeclaredMethod(getMethod, null).getReturnType();
        Method method = clazz.getMethod(this.initSetMethod(outterPro != null ? outterPro : pro), type);
        if (type == String.class) {
            method.invoke(instance, value);
        } else if (type == int.class || type == Integer.class) {
            method.invoke(instance, Integer.parseInt("".equals(value) ? "0" : value));
        } else if (type == long.class || type == Long.class) {
            method.invoke(instance, Long.parseLong("".equals(value) ? "0" : value));
        } else if (type == float.class || type == Float.class) {
            method.invoke(instance, Float.parseFloat("".equals(value) ? "0" : value));
        } else if (type == double.class || type == Double.class) {
            method.invoke(instance, Double.parseDouble("".equals(value) ? "0" : value));
        } else if (type == Date.class) {
            method.invoke(instance, dateFormat.parse(value));
        } else if (type == boolean.class || type == Boolean.class) {
            method.invoke(instance, Boolean.parseBoolean("".equals(value) ? "false" : value));
        } else if (type == byte.class || type == Byte.class) {
            method.invoke(instance, Byte.parseByte(value));
        } else {
            // 引用类型数据
            Object ins = storage.getInstance(instance.hashCode() + outterPro);
            this.setEntity(ins.getClass(), ins, innerPro, value);
            method.invoke(instance, ins);
        }
    }
 
    /**
     * 初始化set方法
     *
     * @param field
     * @return
     */
    private String initSetMethod(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
 
    /**
     * 初始化get方法
     *
     * @param field
     * @return
     */
    private String initGetMethod(String field) {
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
 
 
    /**
     * 处理2003、2007兼容问题
     *
     * @param inp
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    private Workbook create(InputStream inp) throws IOException, InvalidFormatException {
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if (POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("当前Excel版本poi不能解析!");
    }
 
 
    /**
     * 存储bean中的bean成员变量内部类
     */
    class BeanStorage {
 
        private Map<String, Object> instances = new HashMap<String, Object>();
 
        public void storeClass(String key, Class<?> clazz) throws Exception {
            if (!instances.containsKey(key)) {
                instances.put(key, clazz.newInstance());
            }
        }
 
        public Object getInstance(String key) {
            return instances.get(key);
        }
    }
 
}
```

EntityListToExcelUtil.java：

```
package com.hanxiaozhang.utils;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
 
import static java.util.regex.Pattern.compile;
 
 
/**
 * 功能描述: <br>
 * 〈导出Excel〉
 *
 * @Author:hanxinghua
 * @Date: 2020/2/25
 */
public class EntityListToExcelUtil {
 
 
    private StringBuffer error = new StringBuffer(0);
 
 
    private EntityListToExcelUtil(){}
 
    private static class Holder{
        private static  EntityListToExcelUtil INSTANCE=new EntityListToExcelUtil();
    }
 
    public static EntityListToExcelUtil getInstance(){
        return Holder.INSTANCE;
    }
 
 
    /**
     * 将实体类列表entityList转换成excel
     * 2003- 版本的excel
     *
     * @param attrToTitle      包含headMapping信息，key为属性名，value为列名<br>
     * @param entityList
     * @param excel
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IOException
     */
    @Deprecated
    private <T> boolean executeXLS(Map<String, String> attrToTitle, List<T> entityList, OutputStream excel) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        System.out.println(excel.toString());
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        int i = 0;
        List<String> proList = new ArrayList<String>();
        HSSFFont blueFont = workbook.createFont();
        blueFont.setColor(HSSFColor.BLUE.index);
        for (Map.Entry<String, String> entry : attrToTitle.entrySet()) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(entry.getValue());
            text.applyFont(blueFont);
            cell.setCellValue(text);
            proList.add(entry.getKey());
            i++;
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = entityList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            for (i = 0; i < proList.size(); i++) {
                HSSFCell cell = row.createCell(i);
                String propertyName = proList.get(i);
                String textValue = null;
                try {
                    textValue = this.getPropertyValue(t, propertyName);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.error.append("第").append(index + 1).append("行，列名：").append(attrToTitle.get(propertyName)).append("，字段：").append(propertyName).append("，数据错误，跳过！").append("<br>");
                }
                // 利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null) {
                    Pattern p = compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        HSSFRichTextString richString = new HSSFRichTextString(
                                textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
 
        }
        workbook.write(excel);
        //关闭输出流
        excel.close();
        return true;
    }
 
 
    /**
     * 将实体类列表entityList转换成excel
     * 2007+ 版本的excel
     *
     * @param attrToTitle      包含headMapping信息，key为属性名，value为列名<br>
     * @param entityList
     * @param excel
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IOException
     */
    public  <T> boolean executeXLSX(Map<String, String> attrToTitle, List<T> entityList, OutputStream excel) throws NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, IOException {
 
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet("sheet1");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(30);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
 
        int i = 0;
        List<String> proList = new ArrayList<>();
        //设置单元格格式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        //设置字体
        XSSFFont blueFont = workbook.createFont();
        blueFont.setColor(IndexedColors.BLUE.getIndex());
        //设置表头
        Iterator<Map.Entry<String, String>> itr = attrToTitle.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry<String, String> entry = itr.next();
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(entry.getValue());
            text.applyFont(blueFont);
            cell.setCellValue(text);
            proList.add(entry.getKey());
            i++;
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = entityList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，动态调用getXxx()方法得到属性值
            for (i = 0; i < proList.size(); i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                String propertyName = proList.get(i);
                String textValue = null;
                try {
                    textValue = this.getPropertyValue(t, propertyName);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.error.append("第").append(index + 1).append("行，列名：").append(attrToTitle.get(propertyName)).append("，字段：").append(propertyName).append("，数据错误，跳过！").append("<br>");
                }
                // 利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null) {
                    Pattern p = compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
 
        }
        workbook.write(excel);
        //关闭输出流
        excel.close();
        return true;
    }
 
 
    /**
     * 获取实体instance的propertyName属性的值
     *
     * @param instance
     * @param propertyName
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public <T> String getPropertyValue(T instance, String propertyName)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
 
        String getMethodName = this.initGetMethod(propertyName);
        Class<?> tCls = instance.getClass();
        Method getMethod = null;
        Object value = null;
 
        getMethod = tCls.getMethod(getMethodName, new Class[]{});
        value = getMethod.invoke(instance, new Object[]{});
 
        String returnType = getMethod.getReturnType().getName();
 
        // 判断值的类型后进行强制类型转换
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String textValue = "";
        if(value==null){
            return textValue;
        }
        if ("java.util.Date".equals(returnType)) {
            textValue = dateFormat.format(value);
        } else {
            textValue = value.toString();
        }
        return textValue;
    }
 
 
    /**
     * 返回fiel属性的getXXX方法字符串
     *
     * @param field
     * @return
     */
    private String initGetMethod(String field) {
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }
 
 
    /**
     * @return true 存在错误，false 不存在错误
     */
    public boolean hasError() {
        return error.capacity() > 0;
    }
 
 
    /**
     * 获得错误信息
     *
     * @return
     */
    public StringBuffer getError() {
        return error;
    }
 
 
 
 
}
```

### 2. 多线程处理 + 多线程事务回滚模块： 

在实际的环境中，导入的 Excel 可能数据量很大，如果已经对代码进行批量插入、重复数据只查一次、代码逻辑等优化，还没有降低程序处理的时间，那我们就可以考虑使用多线程来处理数据。但是，多线程是一把双刃剑，使用得当则提高处理能力，降低程序运行时间；反之，会造成线程不安全，脏数据等致命问题。在单线程处理数据时，我们通常使用事务来避免脏数据，但是在多线程下事务可能就不好用了，原因是数据交给了多个线程去处理，事务只能控制一个线程内的数据，一个线程出错了，另一个线程的数据可能已经出入库了，破坏了数据的完整性。如何在多线程下使事务回滚呢？这里需要用到同步锁、等待与通知、Volatile 关键字等相关知识。原理是这样：我们创建一个多线程结束标识的类（MultiThreadEndFlag.java），这个类会有线程总个数、失败线程数据、是否全部成功等属性（它们都使用 Volatile 关键字，保证线程安全），每个线程执行完业务操作后都会调用此类等待结束的方法（synchronized void waitForEnd(int resultFlag)），说明自己已经完成业务，waitForEnd 方法会调用 wait()，使线程停止后面代码的运行，放弃 CUP 运行时间进入等待池。如果每个线程都运行完业务逻辑，代码会调用多线程标识类的结束方法（end()）,end 方法会调用 notifyAll()，通知所有线程继续运行，这时，每个线程会通过多线程标识类中是否全部成功属性去判断是否成功，如果不是成功，线程自己会自己抛出异常，使事务回滚。上面讲了如何使多线程事务回滚，下面说一说如何使用多线程，我这里会用到 Executor 相关线程池的知识，如何不太熟悉可以自己去学习一下。此外，我这套方法还支持业务判断有问题数据导出并提示错误原因的功能，该功能主要是通过 Callable 创建线程，可以通过 Future 获取返回值的知识实现的，具体代码如下：

MultiThreadEndFlag.java:

```
package com.hanxiaozhang.importexcel;
 
import lombok.extern.slf4j.Slf4j;
 
import java.util.UUID;
 
 
/**
 * 功能描述: <br>
 * 〈多线程结束标志〉
 *
 * @Author:hanxinghua
 * @Date: 2020/2/23
 */
@Slf4j
public class MultiThreadEndFlag {
 
    /**
     * 是否解除等待
     */
    private volatile boolean releaseWaitFlag = false;
 
    /**
     * 是否全部执行成功
     */
    private volatile boolean allSuccessFlag = false;
 
    /**
     * 线程个数
     */
    private volatile int threadCount = 0;
 
    /**
     * 失败个数
     */
    private volatile int failCount = 0;
 
    /**
     * 初始化子线程的总数
     * @param count
     */
    public MultiThreadEndFlag(int count){
        threadCount = count;
    }
 
 
    public boolean allSuccessFlag() {
        return allSuccessFlag;
    }
 
    /**
     * 等待全部结束
     * @param resultFlag
     */
    public synchronized void waitForEnd(int resultFlag){
        //统计失败的线程个数
        if(resultFlag==0){
            failCount++;
        }
        threadCount--;
        while (!releaseWaitFlag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 执行结束通知
     */
    public synchronized void go(){
        releaseWaitFlag = true;
        //结果都显示成功
        allSuccessFlag = (failCount == 0);
        notifyAll();
    }
    /**
     * 等待结束
     */
    public void end(){
        while (threadCount > 0){
            waitFunc(50);
        }
        log.info("线程全部执行完毕通知");
        go();
    }
 
    /**
     * 等待
     */
    private void waitFunc(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

ImportExcelTask.java:

```
package com.hanxiaozhang.importexcel;
 
 
 
import java.util.List;
import java.util.concurrent.Callable;
 
/**
 * 〈功能描述〉<br>
 * 〈导入Excel任务〉
 *
 * @author hanxinghua
 * @create 2020/2/23
 * @since 1.0.0
 */
public class ImportExcelTask<T> implements Callable<ErrorInfoEntity> {
 
    /**
     * 保存Excel服务
     */
    private SaveExcelService excelService;
 
    /**
     * 数据集合
     */
    private List<T> list;
 
    /**
     * 多线程数据结束标志
     */
    private MultiThreadEndFlag flag;
 
    /**
     * 构造函数
     *
     * @param excelService
     * @param list
     * @param flag
     */
    public ImportExcelTask(SaveExcelService<T> excelService,List<T> list,MultiThreadEndFlag flag){
        this.excelService=excelService;
        this.list=list;
        this.flag=flag;
    }
 
 
    @Override
    public ErrorInfoEntity call() throws Exception {
        return excelService.batchSave(list,flag);
    }
 
 
}
```

ErrorInfoEntity.java:

```
package com.hanxiaozhang.importexcel;
 
import lombok.Data;
 
import java.util.List;
 
/**
 * 〈一句话功能简述〉<br>
 * 〈错误信息实体〉
 *
 * @author hanxinghua
 * @create 2020/2/23
 * @since 1.0.0
 */
@Data
public class ErrorInfoEntity<T> {
 
    /**
     * 业务上判断有错误的数据集合
     */
    private List<T> errorList;
 
 
}
```

SaveExcelService.java:

```
package com.hanxiaozhang.importexcel;
 
import java.util.List;
 
 
/**
 * 〈一句话功能简述〉<br>
 * 〈保存ExcelService〉
 *
 * @author hanxinghua
 * @create 2020/2/23
 * @since 1.0.0
 */
 
public interface SaveExcelService<T> {
 
 
    ErrorInfoEntity batchSave(List<T> list, MultiThreadEndFlag flag) throws Exception;
 
}
```

DictSaveExcelServiceImpl.java:

```
package com.hanxiaozhang.importexcel;
 
import com.hanxiaozhang.dictcode.dao.DictOneDao;
import com.hanxiaozhang.dictcode.domain.DictDO;
import com.hanxiaozhang.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
 
/**
 * 〈一句话功能简述〉<br>
 * 〈数据字典数据导入〉
 *
 * @author hanxinghua
 * @create 2020/2/23
 * @since 1.0.0
 */
@Slf4j
@Service
public class DictSaveExcelServiceImpl implements SaveExcelService<DictDO> {
 
 
    @Resource
    private DictOneDao dictOneDao;
 
 
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ErrorInfoEntity batchSave(List<DictDO> list, MultiThreadEndFlag flag) throws Exception {
        int resultFlag = 0;
        try {
            //创建返回错误信息实体
            ErrorInfoEntity errorInfoEntity = new ErrorInfoEntity();
            //业务操作
            List<DictDO> errorList = handleDict(list);
            //赋值错误数据
            errorInfoEntity.setErrorList(errorList);
            //操作成功
            resultFlag = 1;
            //等待其他线程完成操作
            flag.waitForEnd(resultFlag);
            //其他线程异常手工回滚
            if (resultFlag == 1 && !flag.allSuccessFlag()) {
                String message = "子线程未全部执行成功，对线程[" + Thread.currentThread().getName() + "]进行回滚";
                log.info(message);
                throw new Exception(message);
            }
            return errorInfoEntity;
        } catch (Exception e) {
            log.error(e.toString());
            //本身线程异常抛出异常，并且没有调用flag.waitForEnd()时触发
            if (resultFlag == 0) {
                flag.waitForEnd(resultFlag);
            }
            throw e;
        }
    }
 
 
    /**
     * 处理相关数据
     *
     * @param list
     * @return
     */
    private List<DictDO> handleDict(List<DictDO> list) {
        List<DictDO> errorList=new ArrayList<>();
        list.forEach(x->{
            boolean flag=true;
            List<String> errorMsg=new ArrayList<>();
            //模拟一个业务数据错误，姓名不能为空
            if (StringUtil.isBlank(x.getName())) {
                errorMsg.add("姓名不能为空!");
                flag=false;
            }
            //模拟一个业务数据错误，类型不能为空
            if (StringUtil.isBlank(x.getType())){
                errorMsg.add("类型不能为空!");
                flag=false;
            }
            if (flag){
                dictOneDao.save(x);
            }else {
                x.setRemarks(String.join("\n",errorMsg));
                errorList.add(x);
            }
        });
 
        return errorList;
    }
 
 
}
```

ImportExcelExecutor.java:

```
package com.hanxiaozhang.importexcel;
 
import lombok.extern.slf4j.Slf4j;
 
 
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
 
import static java.util.concurrent.Executors.*;
 
/**
 * 〈一句话功能简述〉<br>
 * 〈导入Excel执行器〉
 *
 * @author hanxinghua
 * @create 2020/2/23
 * @since 1.0.0
 */
@Slf4j
public class ImportExcelExecutor {
 
 
    private static int maxThreadCount=10;
 
 
    /**
     * 执行方法(分批创建子线程)
     * @param saveService 保存的服务
     * @param lists 数据List
     * @param groupLen 分组的长度
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static <T>  List<T>  execute(SaveExcelService<T> saveService, List<T> lists, int groupLen) throws ExecutionException, InterruptedException {
 
        if(lists==null || lists.size()==0){
            return null;
        }
 
        List<T> errorList=new ArrayList<>();
 
        //创建一个线程池，最大10个线程
        ExecutorService executorService = newFixedThreadPool(maxThreadCount);
        //创建一个Future集合
        List<Future<ErrorInfoEntity>> futures = new ArrayList<>();
        //集合的元素个数
        int size = lists.size();
 
        //适配线程池数与分组长度
        //Math.ceil()对小数向下“”取整”
        int batches = (int) Math.ceil(size * 1.0 /groupLen);
        //分组超长最大线程限制，则设置分组数为10，计算分组集合尺寸
        if(batches>maxThreadCount){
            batches = maxThreadCount;
            groupLen = (int) Math.ceil(size * 1.0 /batches);
        }
        log.info("总条数：[{}],批次数量：[{}],每批数据量：[{}]",size,batches,groupLen);
 
 
        MultiThreadEndFlag flag = new MultiThreadEndFlag(batches);
 
        int startIndex, toIndex, maxIndex = lists.size();
 
        for(int i=0;i<batches;i++){
            //开始索引位置
            startIndex = i * groupLen;
            //截止索引位置
            toIndex = startIndex + groupLen;
            //如果截止索引大于最大索引，截止索引等于最大索引
            if(toIndex> maxIndex) {
                toIndex = maxIndex;
            }
            //截取数组
            List<T> temp = lists.subList(startIndex,toIndex);
            if(temp == null || temp.size()==0){
                continue;
            }
            futures.add(executorService.submit(new ImportExcelTask(saveService,temp,flag)));
        }
        flag.end();
 
        //子线程全部等待返回(存在异常，则直接抛向主线程)
        for(Future<ErrorInfoEntity> future:futures){
            errorList.addAll(future.get().getErrorList());
        }
        //所有线程返回后，关闭线程池
        executorService.shutdown();
 
        return errorList;
    }
}
```

### 3. 使用方法： 

使用这块也没有什么好说的，大家的使用方法都类型。这块要说一点 Ajax 不支持下载的功能，如果不知道原因，可以自己搜索一下。我这块会用到下载，大家可以去代码中看一下怎么处理的，相关代码如下：

Controller：

```
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
        R r = dictService.importExcel(file);
 
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
                        JsonUtil.jsonToList(params.get("errorData").toString(), DictDO.class),
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
```

Service：

```
@Override
    @Transactional(rollbackFor = Exception.class)
    public R importExcel(MultipartFile file) {
 
        try {
            //读取Excel中数据
            ArrayList<DictDO> list = ExcelToEntityListUtil.getInstance().execute(DictDO.class, file.getInputStream(), initTitleToAttr());
            //多线程处理数据，并导出错误数据
            List<DictDO> errorList = ImportExcelExecutor.execute(dictSaveExcelServiceImpl, list, 10);
            //封装错误数据
            if (errorList!=null&&!errorList.isEmpty()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("errorData", errorList);
                map.put("title", initAttrToTitle());
                map.put("fileName", "有问题数据.xlsx");
                return R.error(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
 
    private Map<String,String> initTitleToAttr(){
        Map<String, String> map = new LinkedHashMap<>(8);
        map.put("姓名","name");
        map.put("值","value");
        map.put("类型","type");
        map.put("描述","description");
        map.put("时间","createDate");
        return map;
    }
 
    private Map<String,String> initAttrToTitle(){
        Map<String, String> map = new LinkedHashMap<>(8);
        map.put("name","姓名");
        map.put("value","值");
        map.put("type","类型");
        map.put("description","描述");
        map.put("createDate","时间");
        map.put("remarks","数据问题备注");
        return map;
    }
```

页面：

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div >
    <form id="importPlan" method="post" enctype="multipart/form-data" style="float: left">
        <input class="form-control" id="file" >
    </form>
    <button type="button" onclick="importExcel()" style="float: left;margin-right: 10px">导入测试</button>
    <form action="/exportExcel" method="post" id="exportForm"
          style="display: none;">
        <input type="text" />
    </form>
</div>
 
<script type="text/javascript" src="/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript" src="/importExcel.js"></script>
</body>
</html>
```

Js：

```
function importExcel() {
    $.ajax({
        type: "POST",
        dataType: "json",
        cache: false,
        processData: false,
        contentType: false,
        url: "/importExcel",
        data: new FormData($('#importPlan')[0]),
        success: function (r) {
            if (r.code == 0) {
                alert("导入成功");
            } else {
                $("#data").val(JSON.stringify(r.map));
                $("#exportForm").submit();
            }
        }
    });
}
```

四、源码地址：
-------

[https://gitee.com/hanxiaozhang2018/springboot_demo.git](https://gitee.com/hanxiaozhang2018/springboot_demo.git)   springboot-multi-thread 模块