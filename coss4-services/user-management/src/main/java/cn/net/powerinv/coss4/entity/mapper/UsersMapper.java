package cn.net.powerinv.coss4.entity.mapper;

import cn.net.powerinv.coss4.entity.Users;
import cn.net.powerinv.coss4.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     * 查询
     * @param record 记录（查询条件）
     * @return 查询结果
     */
    UserVO selectSelective(Users record);
}