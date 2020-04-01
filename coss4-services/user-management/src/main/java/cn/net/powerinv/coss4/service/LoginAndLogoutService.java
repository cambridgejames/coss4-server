package cn.net.powerinv.coss4.service;

import cn.net.powerinv.coss4.vo.ThirdPartyDTO;
import cn.net.powerinv.coss4.vo.UserDTO;

import java.util.Map;

/**
 * 用户基本信息维护服务
 */
public interface LoginAndLogoutService {
    /**
     * 用户名/邮箱登录
     * @param userDTO 用户信息
     * @return 登录结果
     */
    public Map<String, Object> loginWithUsualInfo(UserDTO userDTO);

    /**
     * 手机短信快捷登录
     * @param userDTO 用户信息
     * @return 登录结果
     */
    public Map<String, Object> loginWithPhone(UserDTO userDTO);

    /**
     * 第三方登录
     * @param thirdPartyDTO 第三方平台信息
     * @return 登录结果
     */
    public Map<String, Object> loginWithThirdPart(ThirdPartyDTO thirdPartyDTO);

    /**
     * 登出
     * @return 登出结果
     */
    public Map<String, Object> logout();
}
