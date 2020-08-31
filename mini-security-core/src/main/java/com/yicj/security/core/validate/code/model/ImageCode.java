package com.yicj.security.core.validate.code.model;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * ClassName: ImageCode
 * Description: TODO(描述)
 * Date: 2020/8/31 15:44
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
public class ImageCode extends ValidateCode{
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }
}