package cn.net.powerinv.coss4.dictionary.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.dictionary.mapper.SystemDictionaryMapper;
import cn.net.powerinv.coss4.dictionary.service.BaseDictionaryService;
import cn.net.powerinv.coss4.dictionary.vo.DictionaryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BaseDictionaryServiceImpl implements BaseDictionaryService {

    private SystemDictionaryMapper systemDictionaryMapper;

    @Autowired
    public void setSystemDictionaryMapper(SystemDictionaryMapper systemDictionaryMapper) {
        this.systemDictionaryMapper = systemDictionaryMapper;
    }

    /**
     * 在系统字典中获取指定字段
     *
     * @param keyWord 字段名称
     * @return 字段列表
     */
    @Override
    public Map<String, Object> getSystemDictionary(String keyWord) {
        List<DictionaryVO> dictionaryVOList = systemDictionaryMapper.getSystemDictionary(keyWord);
        return CommonResultUtil.returnTrue(dictionaryVOList);
    }
}
