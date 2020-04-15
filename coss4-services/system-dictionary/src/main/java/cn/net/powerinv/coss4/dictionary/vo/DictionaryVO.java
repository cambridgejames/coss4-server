package cn.net.powerinv.coss4.dictionary.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryVO {
    private String keyWord;
    private String value;
    private String description;
}
