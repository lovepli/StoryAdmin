package com.story.storyadmin.web.mongoTest;

import com.story.storyadmin.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2020/7/3
 * @description: 测试MongoTemplate https://blog.csdn.net/larger5/article/details/79760317
 */
@RestController
@RequestMapping("/mongoDBTest")
public class MongoDBTestController extends BaseController {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 1、增
     * @param
     * @return
     */
    @PostMapping("/insert")
    public Student insertStudent(/*Student student*/) {
        Student student =new Student("1","张三","123456");
        // 可以用 save 替代
        mongoTemplate.insert(student);
        logger.info("添加成功！");
        return student;
    }

    /**
     * 2、查
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Student getStudentById(@PathVariable String id) {
        return mongoTemplate.findById(id, Student.class);
    }

    /**
     * 3、删
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Student deleteStudentById(@PathVariable String id) {
        Student user = mongoTemplate.findById(id, Student.class);
        mongoTemplate.remove(user);
        return user;
    }

    /**
     * 4、改
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Student updateStudent(Student user) {
        Student student =new Student("1","张三-改-李四","123456");
        // save 可增可改！
        mongoTemplate.save(user);
        return user;
    }

    /**
     * 5、全
     * @return
     */
    @GetMapping("/get/all")
    public List<Student> getAllStudents() {
        return mongoTemplate.findAll(Student.class);
    }

    /**
     * 6、查 ++：属性、分页
     * @param user
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/select/{page}/{size}")
    public Map<String, Object> selectStudentByProperty(Student user, @PathVariable int page, @PathVariable int size) {

        // 条件
        Criteria criteria1 = Criteria.where("studentName").is(user.getStudentName());
        Criteria criteria2 = Criteria.where("password").is(user.getPassword());
        Query query = new Query();
        if (user.getStudentName() != null) {
            query.addCriteria(criteria1);
        }
        if (user.getPassword() != null) {
            query.addCriteria(criteria2);
        }

        // 数量
        long total = mongoTemplate.count(query, Student.class);

        // 分页
        query.skip((page - 1) * size).limit(size);

        List<Student> data = mongoTemplate.find(query, Student.class);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("data", data);
        map.put("total", total);

        return map;
    }
    


}
