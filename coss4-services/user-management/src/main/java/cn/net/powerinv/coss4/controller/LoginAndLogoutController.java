package cn.net.powerinv.coss4.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.service.LoginAndLogoutService;
import cn.net.powerinv.coss4.vo.ThirdPartyDTO;
import cn.net.powerinv.coss4.vo.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户基本信息管理模块
 * @author PengJQ
 * @date 2020-03-31
 */
@Slf4j
@RestController
@RequestMapping("/entry")
public class LoginAndLogoutController {

    private LoginAndLogoutService loginAndLogoutService;

    @Autowired
    public void setLoginAndLogoutService(LoginAndLogoutService loginAndLogoutService) {
        this.loginAndLogoutService = loginAndLogoutService;
    }

    /**
     * 用户名/邮箱登录
     * @param userDTO 用户信息
     * @return 登录结果
     */
    @PostMapping("/loginWithUsualInfo")
    @ResponseBody
    public Map<String, Object> loginWithUsualInfo(@RequestBody UserDTO userDTO) {
        if (StringUtils.isEmpty(userDTO.getPassword())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        if (StringUtils.isEmpty(userDTO.getUsername()) && StringUtils.isEmpty(userDTO.getEMail())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return loginAndLogoutService.loginWithUsualInfo(userDTO);
    }

    /**
     * 手机短信快捷登录
     * @param userDTO 用户信息
     * @return 登录结果
     */
    @PostMapping("/loginWithPhone")
    @ResponseBody
    public Map<String, Object> loginWithPhone(@RequestBody UserDTO userDTO) {
        if (StringUtils.isEmpty(userDTO.getPassword()) || StringUtils.isEmpty(userDTO.getPhone())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return loginAndLogoutService.loginWithPhone(userDTO);
    }

    /**
     * 第三方登录
     * @param thirdPartyDTO 第三方平台信息
     * @return 登录结果
     */
    @PostMapping("/loginWithThirdPart")
    @ResponseBody
    public Map<String, Object> loginWithThirdPart(@RequestBody ThirdPartyDTO thirdPartyDTO) {
        if (ObjectUtils.isEmpty(thirdPartyDTO.getThirdPartyId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return loginAndLogoutService.loginWithThirdPart(thirdPartyDTO);
    }

    /**
     * 登出
     * @return 登出结果
     */
    @PostMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout() {
        return loginAndLogoutService.logout();
    }
}
