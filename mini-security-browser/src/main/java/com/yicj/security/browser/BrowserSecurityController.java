package com.yicj.security.browser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: BrowserSecurityController
 * Description: TODO(描述)
 * Date: 2020/8/31 9:59
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@RestController
public class BrowserSecurityController {

    @GetMapping("/hello")
    public String hello(){

        return "browser hello world";
    }
}