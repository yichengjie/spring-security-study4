package com.yicj.security.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private ObjectMapper objectMapper ;

    @RequestMapping("/")
    //添加如下直接后页面将空白
    //@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String index(){

        return "hello world" ;
    }
}