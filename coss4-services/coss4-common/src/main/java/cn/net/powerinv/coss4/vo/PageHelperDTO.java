package cn.net.powerinv.coss4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageHelperDTO {
    private Integer pageNumber = 1;
    private Integer pageSize = 15;
    private String sortType;
    private boolean sortOrder;  // true = 升序ASC；false - 降序DESC
}
