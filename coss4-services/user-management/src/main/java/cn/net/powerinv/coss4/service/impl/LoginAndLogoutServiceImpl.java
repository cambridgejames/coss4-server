package cn.net.powerinv.coss4.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.entity.Users;
import cn.net.powerinv.coss4.entity.mapper.UsersMapper;
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
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class LoginAndLogoutServiceImpl implements LoginAndLogoutService {

    private UsersMapper usersMapper;

    @Autowired
    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * 用户名/邮箱登录
     *
     * @param userDTO 用户信息
     * @return 登录结果
     */
    @Override
    public Map<String, Object> loginWithUsualInfo(UserDTO userDTO) {
        Users users = new Users();
        users.setUserName(userDTO.getUsername());
        users.seteMail(userDTO.getEMail());
        users.setPasswd(userDTO.getPassword());

        UserVO newUser = usersMapper.selectSelective(users);
        if (newUser == null) {
            return CommonResultUtil.returnFalse(MessageCode.USERNAME_OR_PASSWORD_NOT_TRUE);
        }

        // 将用户信息存入session
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        request.getSession().setAttribute("user", newUser);

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
