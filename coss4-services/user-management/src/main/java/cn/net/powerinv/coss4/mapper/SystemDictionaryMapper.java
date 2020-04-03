package cn.net.powerinv.coss4.mapper;

import cn.net.powerinv.coss4.entity.SystemDictionary;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemDictionary record);

    int insertSelective(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemDictionary record);

    int updateByPrimaryKey(SystemDictionary record);
}