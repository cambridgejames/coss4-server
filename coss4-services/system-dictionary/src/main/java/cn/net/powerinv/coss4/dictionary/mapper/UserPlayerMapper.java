package cn.net.powerinv.coss4.dictionary.mapper;

import cn.net.powerinv.coss4.entity.UserPlayer;

public interface UserPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPlayer record);

    int insertSelective(UserPlayer record);

    UserPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPlayer record);

    int updateByPrimaryKey(UserPlayer record);
}