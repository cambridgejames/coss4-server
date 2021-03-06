package cn.net.powerinv.coss4.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.entity.User;
import cn.net.powerinv.coss4.mapper.UserMapper;
import cn.net.powerinv.coss4.service.LoginAndLogoutService;
import cn.net.powerinv.coss4.vo.ThirdPartyDTO;
import cn.net.powerinv.coss4.vo.UserDTO;
import cn.net.powerinv.coss4.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class LoginAndLogoutServiceImpl implements LoginAndLogoutService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 用户名/邮箱登录
     *
     * @param userDTO 用户信息
     * @return 登录结果
     */
    @Override
    public Map<String, Object> loginWithUsualInfo(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.seteMail(userDTO.getEMail());
        user.setPasswd(userDTO.getPassword());

        User newUser = userMapper.selectSelective(user);
        if (newUser == null) {
            return CommonResultUtil.returnFalse(MessageCode.USERNAME_OR_PASSWORD_NOT_TRUE);
        }

        // 将用户信息存入session
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);

        return CommonResultUtil.returnTrue(newUser);
    }

    /**
     * 手机短信快捷登录
     *
     * @param userDTO 用户信息
     * @return 登录结果
     */
    @Override
    public Map<String, Object> loginWithPhone(UserDTO userDTO) {
        return CommonResultUtil.returnFalse(MessageCode.LOGIN_FAILED);
    }

    /**
     * 第三方登录
     *
     * @param thirdPartyDTO 第三方平台信息
     * @return 登录结果
     */
    @Override
    public Map<String, Object> loginWithThirdPart(ThirdPartyDTO thirdPartyDTO) {
        return CommonResultUtil.returnFalse(MessageCode.LOGIN_FAILED);
    }

    /**
     * 登出
     *
     * @return 登出结果
     */
    @Override
    public Map<String, Object> logout() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        request.getSession().removeAttribute("user");
        return CommonResultUtil.returnTrue();
    }
}
