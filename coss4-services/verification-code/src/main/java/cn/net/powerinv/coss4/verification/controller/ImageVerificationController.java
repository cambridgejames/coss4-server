package cn.net.powerinv.coss4.verification.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.verification.service.ImageVerificationService;
import cn.net.powerinv.coss4.verification.vo.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 图片验证码请求接口
 * @author PengJQ
 * @date 2020-03-29
 */
@Slf4j
@RestController
@RequestMapping("/imageCode")
public class ImageVerificationController {

    private ImageVerificationService imageVerificationService;

    @Autowired
    public void setImageVerificationService(ImageVerificationService imageVerificationService) {
        this.imageVerificationService = imageVerificationService;
    }

    /**
     * 获取图片验证码
     * @return 包含图片验证码的公共返回体
     */
    @GetMapping("/getVerificationCode")
    @ResponseBody
    public Map<String, Object> getVerificationCode() {
        return imageVerificationService.getVerificationCode();
    }

    /**
     * 以区分大小写方式验证图片验证码
     * @param verificationCodeDTO 待验证的图片验证码
     * @return 验证结果（正确/错误）
     */
    @GetMapping("/checkCaseSensitive")
    @ResponseBody
    public Map<String, Object> checkCaseSensitive(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String code = verificationCodeDTO.getCode();
        if (StringUtils.isEmpty(code)) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return imageVerificationService.checkCaseSensitive(code);
    }

    /**
     * 以不区分大小写方式验证图片验证码
     * @param verificationCodeDTO 待验证的图片验证码
     * @return 验证结果（正确/错误）
     */
    @GetMapping("/checkCaseInsensitive")
    @ResponseBody
    public Map<String, Object> checkCaseInsensitive(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String code = verificationCodeDTO.getCode();
        if (StringUtils.isEmpty(code)) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return imageVerificationService.checkCaseInsensitive(code);
    }

}
