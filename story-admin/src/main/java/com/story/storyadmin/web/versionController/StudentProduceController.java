package com.story.storyadmin.web.versionController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description: 通过媒体类型进行版本控制
 */
@RestController
public class StudentProduceController {

    @GetMapping(value="/student/produce",produces = "application/api-v1+json")
    public StudentV1 studentV1() {
        return new StudentV1("javadaily");
    }

    @GetMapping(value="/student/produce",produces = "application/api-v2+json")
    public StudentV2 studentV2() {
        return new StudentV2(new Name("javadaily", "JAVA日知录"));
    }
}