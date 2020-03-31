package cn.net.powerinv.coss4.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.entity.Users;
import cn.net.powerinv.coss4.entity.mapper.UsersMapper;
import cn.net.powerinv.coss4.service.BaseInformationService;
import cn.net.powerinv.coss4.vo.PasswordDTO;
import cn.net.powerinv.coss4.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户基本信息维护服务
 * @author PengJQ
 * @date 2020-03-31
 */
@Slf4j
@Service
public class BaseInformationServiceImpl implements BaseInformationService {

    private UsersMapper usersMapper;

    @Autowired
    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * 用户注册接口
     *
     * @param user 用户基本信息
     * @return 注册操作结果
     */
    @Override
    public Map<String, Object> signUp(Users user) {
        if (usersMapper.insertSelective(user) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.USER_CREATE_FAILED);
        }
        log.info("Created user: UserName -> {}", user.getUserName());
        return CommonResultUtil.returnTrue();
    }

    /**
     * 查询用户基本信息
     *
     * @param uid 用户UID
     * @return 用户基本信息查询结果
     */
    @Override
    public Map<String, Object> queryBaseInfo(Integer uid) {
        Users user = usersMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.USER_NOT_FOUND);
        }
        UserVO userVO = new UserVO();
        userVO.setUid(user.getId());
        userVO.setUsername(user.getUserName());
        userVO.setSex(user.getSex());
        userVO.setNickname(user.getNickname());
        userVO.setAvatar(user.getAvatarImage());
        userVO.setPhone(user.getPhoneNumber());
        userVO.setEMail(user.geteMail());
        userVO.setCreateTime(user.getCreateTime());
        userVO.setUpdateTime(user.getUpdateTime());
        return CommonResultUtil.returnTrue(userVO);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 更新后的用户基本信息
     * @return 信息更新结果
     */
    @Override
    public Map<String, Object> updateBaseInfo(Users user) {
        if (usersMapper.updateByPrimaryKeySelective(user) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        log.info("User[uid -> {}] updated base information", user.getId());
        return CommonResultUtil.returnTrue();
    }

    /**
     * 修改密码
     *
     * @param passwordDTO 新/旧密码
     * @return 修改结果（成功/失败）
     */
    @Override
    public Map<String, Object> updatePassword(PasswordDTO passwordDTO) {
        if (usersMapper.updatePassword(passwordDTO) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        log.info("User[uid -> {}] updated password", passwordDTO.getUid());
        return CommonResultUtil.returnTrue();
    }

    /**
     * 查询用户名是否已经存在
     *
     * @param username 要查询的用户名
     * @return 查询结果
     */
    @Override
    public Map<String, Object> isUsernameExist(String username) {
        Users user = new Users();
        user.setUserName(username);
        if(usersMapper.selectSelective(user) != null) {
            return CommonResultUtil.returnFalse(MessageCode.USERNAME_ALREADY_EXIST);
        }
        return CommonResultUtil.returnTrue();
    }
}
