package cn.net.powerinv.coss4.service;

import java.util.Map;

/**
 * 图片验证码请求服务
 * @author PengJQ
 * @date 2020-03-29
 */
public interface ImageVerificationService {
    /**
     * 获取图片验证码
     * @return 包含图片验证码的公共返回体
     */
    public Map<String, Object> getVerificationCode();

    /**
     * 以区分大小写方式验证图片验证码
     * @param code 待验证的图片验证码
     * @return 验证结果（正确/错误）
     */
    public Map<String, Object> checkCaseSensitive(String code);

    /**
     * 以不区分大小写方式验证图片验证码
     * @param code 待验证的图片验证码
     * @return 验证结果（正确/错误）
     */
    public Map<String, Object> checkCaseInsensitive(String code);
}
