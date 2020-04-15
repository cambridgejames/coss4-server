package cn.net.powerinv.coss4.dictionary.mapper;

import cn.net.powerinv.coss4.entity.CompRubric;

public interface CompRubricMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompRubric record);

    int insertSelective(CompRubric record);

    CompRubric selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompRubric record);

    int updateByPrimaryKey(CompRubric record);
}