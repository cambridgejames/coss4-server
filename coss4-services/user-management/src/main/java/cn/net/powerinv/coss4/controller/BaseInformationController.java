package cn.net.powerinv.coss4.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.entity.User;
import cn.net.powerinv.coss4.service.BaseInformationService;
import cn.net.powerinv.coss4.vo.PasswordDTO;
import cn.net.powerinv.coss4.vo.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 用户基本信息维护服务
 * @author PengJQ
 * @date 2020-03-31
 */
@Slf4j
@RestController
@RequestMapping("/baseInfo")
public class BaseInformationController {

    private BaseInformationService baseInformationService;

    @Autowired
    public void setBaseInformationService(BaseInformationService baseInformationService) {
        this.baseInformationService = baseInformationService;
    }

    /**
     * 用户注册接口
     * @param userDTO 用户基本信息
     * @return 注册操作结果
     */
    @ResponseBody
    @PostMapping("/signUp")
    public Map<String, Object> signUp(@RequestBody UserDTO userDTO) {
        if (StringUtils.isEmpty(userDTO.getUsername()) || StringUtils.isEmpty(userDTO.getPassword())
                || ObjectUtils.isEmpty(userDTO.getSex())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        if (StringUtils.isEmpty(userDTO.getNickname())) {
            userDTO.setNickname(userDTO.getUsername()); // 默认昵称与用户名相同
        }
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setPasswd(userDTO.getPassword());
        user.setSex(userDTO.getSex() ? 1 : 0);
        user.setNickname(userDTO.getNickname());
        user.setAvatarImage(userDTO.getAvatar());
        user.setRoleGroup(1);    // 1 - 普通用户
        user.setPhoneNumber(userDTO.getPhone());
        user.seteMail(userDTO.getEMail());
        user.setCreateTime(new Date());
        return baseInformationService.signUp(user);
    }

    /**
     * 查询用户基本信息
     * @param userDTO 用户UID
     * @return 用户基本信息查询结果
     */
    @ResponseBody
    @GetMapping("/queryBaseInfo")
    public Map<String, Object> queryBaseInfo(@RequestBody UserDTO userDTO) {
        if (ObjectUtils.isEmpty(userDTO.getUid())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return baseInformationService.queryBaseInfo(userDTO.getUid());
    }

    /**
     * 修改用户基本信息（不能修改密码）
     * @param userDTO 更新后的用户基本信息
     * @return 信息更新结果
     */
    @ResponseBody
    @PostMapping("/updateBaseInfo")
    public Map<String, Object> updateBaseInfo(@RequestBody UserDTO userDTO) {
        if (ObjectUtils.isEmpty(userDTO.getUid())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        User user = new User();
        user.setId(userDTO.getUid());
        user.setUserName(userDTO.getUsername());
        if (!ObjectUtils.isEmpty(userDTO.getSex())) {
            user.setSex(userDTO.getSex() ? 1 : 0);
        }
        user.setNickname(userDTO.getNickname());
        user.setAvatarImage(userDTO.getAvatar());
        user.setPhoneNumber(userDTO.getPhone());
        user.seteMail(userDTO.getEMail());
        user.setUpdateTime(new Date());
        return baseInformationService.updateBaseInfo(user);
    }

    /**
     * 修改密码
     * @param passwordDTO 新/旧密码
     * @return 修改结果（成功/失败）
     */
    @ResponseBody
    @PostMapping("/updatePassword")
    public Map<String, Object> updatePassword(@RequestBody PasswordDTO passwordDTO) {
        if (ObjectUtils.isEmpty(passwordDTO.getUid()) || StringUtils.isEmpty(passwordDTO.getNewPassword())
                || StringUtils.isEmpty(passwordDTO.getOldPassword())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        // 新旧密码不能相同
        if (passwordDTO.getNewPassword().equals(passwordDTO.getOldPassword())) {
            return CommonResultUtil.returnFalse(MessageCode.PASSWORD_CANT_SAME);
        }
        passwordDTO.setUpdateTime(new Date());
        return baseInformationService.updatePassword(passwordDTO);
    }

    /**
     * 查询用户名是否已经存在
     * @param userDTO 要查询的用户名
     * @return 查询结果
     */
    @ResponseBody
    @GetMapping("/isUsernameExist")
    public Map<String, Object> isUsernameExist(@RequestBody UserDTO userDTO) {
        if (StringUtils.isEmpty(userDTO.getUsername())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return baseInformationService.isUsernameExist(userDTO.getUsername());
    }
}
