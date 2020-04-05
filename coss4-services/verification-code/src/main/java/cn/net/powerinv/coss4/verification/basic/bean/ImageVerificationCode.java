package cn.net.powerinv.coss4.verification.basic.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片验证码对象，存储图片（Base64）和对应的编码
 * @author PengJQ
 * @date 2020-03-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageVerificationCode {
    private String image;
    private String code;
}
