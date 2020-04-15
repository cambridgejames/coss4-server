package cn.net.powerinv.coss4.dictionary.service;

import java.util.Map;

public interface BaseDictionaryService {

    /**
     * 在系统字典中获取指定字段
     * @param keyWord 字段名称
     * @return 字段列表
     */
    Map<String, Object> getSystemDictionary(String keyWord);
}
