package cn.net.powerinv.coss4.dictionary.mapper;

import cn.net.powerinv.coss4.dictionary.vo.DictionaryVO;
import cn.net.powerinv.coss4.entity.SystemDictionary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemDictionary record);

    int insertSelective(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemDictionary record);

    int updateByPrimaryKey(SystemDictionary record);

    /**
     * 根据关键词查询字典
     * @param keyWord 关键词
     * @return 字典列表
     */
    List<DictionaryVO> getSystemDictionary(String keyWord);
}