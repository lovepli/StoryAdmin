package com.story.storyadmin.domain.entity.children;

import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO.PersonDO;
import com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO.TypeHandlerDo;
import com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO.UserStateEnum;
import com.story.storyadmin.enumerator.globalEnum.GenderEnum;
import com.story.storyadmin.mapper.children.TypeHandlerMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author: lipan
 * @date: 2021年09月08日 4:16 下午
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTypeHandlerTest extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisTypeHandlerTest.class);


    @Autowired
    TypeHandlerMapper typeHandlerMapper;

    @Test
    public void Test1() {

        TypeHandlerDo typeHandlerDo = new TypeHandlerDo();
        //typeHandlerDo.setCreateTime(new Date());
        typeHandlerDo.setUserStateEnum(UserStateEnum.BAN); //禁用

        Set<Integer> ids=new HashSet<>();
        ids.addAll(Arrays.asList(new Integer(1),new Integer(2),new Integer(3)));
        typeHandlerDo.setIds(ids);

        //添加联系方式
        Map<String, String> contact = new HashMap<>();
        contact.put("phone", "010-1234567");
        contact.put("tel", "13388889999");

        JSONObject json=new JSONObject();
        json.putAll(contact);
        typeHandlerDo.setContact(json);

        typeHandlerDo.setGender(GenderEnum.MALE);

        PersonDO personDo =new PersonDO();
        personDo.setName("张三");
        personDo.setAge(11);
        typeHandlerDo.setPersonDO(personDo);
        typeHandlerMapper.insert(typeHandlerDo);

    }

    @Test
    public void Test2() {
        TypeHandlerDo typeHandlerDo = typeHandlerMapper.selectById(7);
        //System.out.println(typeHandlerDo);
        //System.out.println(typeHandlerDo.getPersonDO());
        //System.out.println(typeHandlerDo.getPersonDO().getName());
        //######################################################
        System.out.println(typeHandlerDo.getIds());
        //######################################################
        System.out.println(typeHandlerDo.getContact());
        //######################################################
        System.out.println(typeHandlerDo.getGender());
        //######################################################
        //System.out.println(typeHandlerDo.getUserStateEnum());
        //System.out.println(typeHandlerDo.getUserStateEnum().getStateCode());
        //System.out.println(typeHandlerDo.getUserStateEnum().getStateString());
    }
}
