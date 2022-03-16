package com.story.storyadmin.web.versionController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description: Restful API 如何进行版本控制 ？ 这4种方法你要掌握！
 * https://mp.weixin.qq.com/s/wwmQyjXn2hLuxvRqaST2BQ
 * 通过 URI 进行版本控制
 */
@RestController
public class StudentUriController {

    @GetMapping("v1/student")
    public StudentV1 studentV1() {
        return new StudentV1("javadaily");
    }

    @GetMapping("v2/student")
    public StudentV2 studentV2() {
        return new StudentV2(new Name("javadaily", "JAVA日知录"));
    }

}