package com.story.storyadmin.dictonecode;

import com.story.storyadmin.domain.entity.dictonecode.DictOneDO;
import com.story.storyadmin.mapper.dictonecode.DictOneMapper;
import com.story.storyadmin.service.dictonecode.DictOneService;
import com.story.storyadmin.service.importexcel.DictSaveExcelServiceImpl;
import com.story.storyadmin.service.importexcel.ImportExcelExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 一套超好用的“Excel导入导出+多线程处理导入数据+多线程事务回滚”的模板方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public  class SpringbootMultiThreadApplicationTests {

    @Resource
    private DictOneMapper dictOneDao;

    @Autowired
    private DictOneService dictOneService;

    @Autowired
    private DictSaveExcelServiceImpl dictSaveExcelServiceImpl;

    @Test
    public void test(){
        DictOneDO dictDO = new DictOneDO();
        String s = "1";
        dictDO.setName(s);
        dictDO.setValue(s);
        dictDO.setType(s);
        dictOneService.save(dictDO);

    }

    /**
     * 模拟导入Excel数据到数据库
     */
    @Test
    public void importExcelTest(){

        try {
            List<DictOneDO> execute = ImportExcelExecutor.execute(dictSaveExcelServiceImpl, initData(), 100);
            System.out.println(execute.size());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private List<DictOneDO> initData(){

        ArrayList<DictOneDO> dictDOS = new ArrayList<>();
        // 生成500条数据
        for (int i = 0; i < 500; i++) {
            DictOneDO dictDO = new DictOneDO();
            String s = String.valueOf(i);

            // 特殊需要校验的不合法情况
            if((i%13)==0){
                dictDO.setType(s);
            }else if((i%11)==0){
                dictDO.setName(s);
            } else if(i==143){
                dictDO.setValue(s);
            }else {
                //正确情况
                dictDO.setName(s);
                dictDO.setType(s);
                dictDO.setValue(s);
            }

            dictDOS.add(dictDO);
        }

        return dictDOS;
    }

}
