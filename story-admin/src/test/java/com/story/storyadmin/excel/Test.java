package com.story.storyadmin.excel;

import com.alibaba.excel.metadata.Sheet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 史上最全的Excel导入导出（easyexcel版） https://mp.weixin.qq.com/s/IrHSKPtNV7dBhqyIF6bw6Q?
 * @description: 测试类
 * @author: chenmingjian
 * @date: 19-4-4 15:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

   // private  static final String FilePATH ="C:\\Users\\59688\\Desktop\\tp\\测试.xlsx";
   //private static String filePath ;
   //
   // @BeforeAll //在所有测试方法前执行，只执行一次
   // public static void setUp() {
   //     filePath ="C:\\Users\\59688\\Desktop\\tp\\测试.xlsx";
   // }

    /**
     * 读取少于1000行的excle
     */
    @org.junit.Test
    public void readLessThan1000Row(){
       // String filePath = "/home/chenmingjian/Downloads/测试.xlsx";
        String filePath ="C:\\Users\\59688\\Desktop\\tp\\测试.xlsx";
        List<Object> objects = ExcelUtil.readLessThan1000Row(filePath);
        objects.forEach(System.out::println);
    }

    /**
     * 读取少于1000行的excle，可以指定sheet和从几行读起
     * 获取Sheet1表头以下的信息
     */
    @org.junit.Test
    public void readLessThan1000RowBySheet(){
        String filePath ="C:\\Users\\59688\\Desktop\\tp\\测试.xlsx";
        //第一个1代表sheet1, 第二个1代表从第几行开始读取数据，行号最小值为0
        Sheet sheet = new Sheet(1, 1);
        List<Object> objects = ExcelUtil.readLessThan1000RowBySheet(filePath,sheet);
        objects.forEach(System.out::println);
    }

    /**
     *获取Sheet2的所有信息
     */
    @org.junit.Test
    public void readLessThan1000RowBySheet2(){
        String filePath ="C:\\Users\\59688\\Desktop\\tp\\测试.xlsx";
        //第一个1代表sheet1, 第二个1代表从第几行开始读取数据，行号最小值为0
        Sheet sheet = new Sheet(2, 0);
        List<Object> objects = ExcelUtil.readLessThan1000RowBySheet(filePath,sheet);
        objects.forEach(System.out::println);
    }

    /**
     * 读取大于1000行的excle
     * 带sheet参数的方法可参照测试方法readLessThan1000RowBySheet()
     *默认读取
     */
    @org.junit.Test
    public void readMoreThan1000Row(){
        String filePath ="C:\\Users\\59688\\Desktop\\tp\\测试.xlsx";
        List<Object> objects = ExcelUtil.readMoreThan1000Row(filePath);
        objects.forEach(System.out::println);
    }

    /**
     *指定读取
     */
    @org.junit.Test
    public void readMoreThan1000Row2(){
        String filePath ="C:\\Users\\59688\\Desktop\\tp\\测试.xlsx";
        Sheet sheet = new Sheet(1, 2);
        List<Object> objects = ExcelUtil.readMoreThan1000RowBySheet(filePath,sheet);
        objects.forEach(System.out::println);
    }


    /**
     * 导出excle
     * 单个Sheet导出
     * 无模型映射导出
     * 带sheet参数的方法可参照测试方法readLessThan1000RowBySheet()
     */
    @org.junit.Test
    public void writeBySimple(){
        String filePath ="C:\\Users\\59688\\Desktop\\tp\\测试2.xlsx";
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        List<String> head = Arrays.asList("表头1", "表头2", "表头3");
        ExcelUtil.writeBySimple(filePath,data,head);
    }


    /**
     * 生成excle, 带用模型
     * 带sheet参数的方法可参照测试方法readLessThan1000RowBySheet()
     */
    @org.junit.Test
    public void writeWithTemplate(){
        String filePath ="C:\\Users\\59688\\Desktop\\tp\\测试3.xlsx";
        ArrayList<TableHeaderExcelProperty> data = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
            tableHeaderExcelProperty.setName("cmj" + i);
            tableHeaderExcelProperty.setAge(22 + i);
            tableHeaderExcelProperty.setSchool("清华大学" + i);
            data.add(tableHeaderExcelProperty);
        }
        ExcelUtil.writeWithTemplate(filePath,data);
    }


    /**
     * 多个Sheet导出
     * 生成excle, 带用模型,带多个sheet
     */
    @org.junit.Test
    public void writeWithMultipleSheel(){
        ArrayList<ExcelUtil.MultipleSheelPropety> list1 = new ArrayList<>();
        for(int j = 1; j < 4; j++){
            ArrayList<TableHeaderExcelProperty> list = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
                tableHeaderExcelProperty.setName("cmj" + i);
                tableHeaderExcelProperty.setAge(22 + i);
                tableHeaderExcelProperty.setSchool("清华大学" + i);
                list.add(tableHeaderExcelProperty);
            }

            Sheet sheet = new Sheet(j, 0);
            sheet.setSheetName("sheet" + j);

            ExcelUtil.MultipleSheelPropety multipleSheelPropety = new ExcelUtil.MultipleSheelPropety();
            multipleSheelPropety.setData(list);
            multipleSheelPropety.setSheet(sheet);

            list1.add(multipleSheelPropety);

        }

        ExcelUtil.writeWithMultipleSheel("C:\\Users\\59688\\Desktop\\tp\\测试5.xlsx",list1);

    }


}