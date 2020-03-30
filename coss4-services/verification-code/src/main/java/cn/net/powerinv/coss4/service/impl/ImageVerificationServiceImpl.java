package cn.net.powerinv.coss4.service.impl;

import cn.net.powerinv.coss4.basic.bean.ImageVerificationCode;
import cn.net.powerinv.coss4.basic.util.ImageVerificationUtil;
import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.service.ImageVerificationService;
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
        String trueCode = (String) request.getSession().getAttribute("verificationCode");
        return CommonResultUtil.returnTrue(code.equals(trueCode));
    }

    @Override
    public Map<String, Object> checkCaseInsensitive(String code) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String trueCode = (String) request.getSession().getAttribute("verificationCode");
        if (StringUtils.isEmpty(trueCode)) {
            return CommonResultUtil.returnTrue(false);
        }
        return CommonResultUtil.returnTrue(code.toLowerCase().equals(trueCode.toLowerCase()));
    }
}
