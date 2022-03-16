package com.story.storyadmin.web.versionController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description: 通过请求参数进行版本控制
 */
@RestController
public class StudentParmController {

    @GetMapping(value="/student/param",params = "version=1")
    public StudentV1 studentV1() {
        return new StudentV1("javadaily");
    }

    @GetMapping(value="/student/param",params = "version=2")
    public StudentV2 studentV2() {
        return new StudentV2(new Name("javadaily", "JAVA日知录"));
    }
}