package com.yicj.security.core.properties;

import lombok.Data;

/**
 * ClassName: ImageCodeProperties
 * Description: TODO(描述)
 * Date: 2020/8/31 15:47
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }
    /**
     * 图片宽
     */
    private int width = 67;
    /**
     * 图片高
     */
    private int height = 23;
}