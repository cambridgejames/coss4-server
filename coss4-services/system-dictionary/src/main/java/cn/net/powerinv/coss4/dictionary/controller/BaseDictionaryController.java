package cn.net.powerinv.coss4.dictionary.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.dictionary.service.BaseDictionaryService;
import cn.net.powerinv.coss4.dictionary.vo.DictionaryVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dictionary")
public class BaseDictionaryController {

    private BaseDictionaryService baseDictionaryService;

    @Autowired
    public void setBaseDictionaryService(BaseDictionaryService baseDictionaryService) {
        this.baseDictionaryService = baseDictionaryService;
    }

    /**
     * 在系统字典中获取指定字段
     * @param dictionaryVO 字段名称
     * @return 字段列表
     */
    @ResponseBody
    @GetMapping("/getSystemDictionary")
    public Map<String, Object> getSystemDictionary(@RequestBody DictionaryVO dictionaryVO) {
        if (StringUtils.isEmpty(dictionaryVO.getKeyWord())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return baseDictionaryService.getSystemDictionary(dictionaryVO.getKeyWord());
    }

}
