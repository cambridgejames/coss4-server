package cn.net.powerinv.coss4.dictionary.mapper;

import cn.net.powerinv.coss4.entity.Comp;

public interface CompMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comp record);

    int insertSelective(Comp record);

    Comp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comp record);

    int updateByPrimaryKeyWithBLOBs(Comp record);

    int updateByPrimaryKey(Comp record);
}