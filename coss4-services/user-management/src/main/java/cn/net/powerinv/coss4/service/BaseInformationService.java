package cn.net.powerinv.coss4.service;

import cn.net.powerinv.coss4.entity.Users;
import cn.net.powerinv.coss4.vo.PasswordDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

public interface BaseInformationService {
    /**
     * 用户注册接口
     * @param user 用户基本信息
     * @return 注册操作结果
     */
    Map<String, Object> signUp(Users user);

    /**
     * 查询用户基本信息
     * @param uid 用户UID
     * @return 用户基本信息查询结果
     */
    Map<String, Object> queryBaseInfo(Integer uid);

    /**
     * 修改用户基本信息
     * @param user 更新后的用户基本信息
     * @return 信息更新结果
     */
    Map<String, Object> updateBaseInfo(Users user);

    /**
     * 修改密码
     * @param passwordDTO 新/旧密码
     * @return 修改结果（成功/失败）
     */
    Map<String, Object> updatePassword(PasswordDTO passwordDTO);

    /**
     * 查询用户名是否已经存在
     * @param username 要查询的用户名
     * @return 查询结果
     */
    Map<String, Object> isUsernameExist(String username);
}
