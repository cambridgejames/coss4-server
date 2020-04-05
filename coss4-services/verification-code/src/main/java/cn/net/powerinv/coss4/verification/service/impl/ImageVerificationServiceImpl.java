package cn.net.powerinv.coss4.verification.service.impl;

import cn.net.powerinv.coss4.verification.basic.bean.ImageVerificationCode;
import cn.net.powerinv.coss4.verification.basic.util.ImageVerificationUtil;
import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.verification.service.ImageVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class ImageVerificationServiceImpl implements ImageVerificationService {

    @Override
    public Map<String, Object> getVerificationCode() {
        try {
            ImageVerificationCode imageVerificationCode = ImageVerificationUtil.createCode();
            HttpServletRequest request = ((ServletRequestAttributes) Objects
                    .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            request.getSession().setAttribute("verificationCode", imageVerificationCode.getCode());
            return CommonResultUtil.returnTrue(imageVerificationCode.getImage());
        } catch (IOException ioe) {
            log.error(ioe.getMessage(), ioe);
            return CommonResultUtil.returnFalse(MessageCode.FILE_WRITE_ERROR);
        }
    }

    @Override
    public Map<String, Object> checkCaseSensitive(String code) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 如果获取不到验证码或者验证码校验失败，返回请求失败
        String trueCode = (String) request.getSession().getAttribute("verificationCode");
        if (StringUtils.isEmpty(trueCode) || !code.equals(trueCode)) {
            return CommonResultUtil.returnFalse(MessageCode.VERIFICATION_NOT_TRUE);
        }
        return CommonResultUtil.returnTrue();
    }

    @Override
    public Map<String, Object> checkCaseInsensitive(String code) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String trueCode = (String) request.getSession().getAttribute("verificationCode");
        // 如果获取不到验证码或者验证码校验失败，返回请求失败
        if (StringUtils.isEmpty(trueCode) || !code.toLowerCase().equals(trueCode.toLowerCase())) {
            return CommonResultUtil.returnFalse(MessageCode.VERIFICATION_NOT_TRUE);
        }
        return CommonResultUtil.returnTrue();
    }
}
