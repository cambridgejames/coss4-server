package cn.net.powerinv.coss4.entity.mapper;

import cn.net.powerinv.coss4.entity.UserJudger;

public interface UserJudgerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserJudger record);

    int insertSelective(UserJudger record);

    UserJudger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserJudger record);

    int updateByPrimaryKey(UserJudger record);
}