package cn.net.powerinv.coss4.dictionary.mapper;

import cn.net.powerinv.coss4.entity.CompGroup;

public interface CompGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompGroup record);

    int insertSelective(CompGroup record);

    CompGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompGroup record);

    int updateByPrimaryKeyWithBLOBs(CompGroup record);

    int updateByPrimaryKey(CompGroup record);
}