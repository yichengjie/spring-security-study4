package com.yicj.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: IndexController
 * Description: TODO(描述)
 * Date: 2020/8/30 22:49
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "hello world" ;
    }
}