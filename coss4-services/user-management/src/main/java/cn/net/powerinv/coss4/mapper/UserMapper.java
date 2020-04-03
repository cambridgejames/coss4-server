package cn.net.powerinv.coss4.mapper;

import cn.net.powerinv.coss4.entity.User;
import cn.net.powerinv.coss4.vo.PasswordDTO;
import cn.net.powerinv.coss4.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询
     * @param record 记录（查询条件）
     * @return 查询结果
     */
    User selectSelective(User record);

    /**
     * 修改密码
     * @param passwordDTO 新/旧密码
     * @return 密码修改结果
     */
    int updatePassword(PasswordDTO passwordDTO);
}