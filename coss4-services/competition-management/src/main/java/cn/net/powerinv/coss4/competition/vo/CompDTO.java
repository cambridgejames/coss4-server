package cn.net.powerinv.coss4.competition.vo;

import cn.net.powerinv.coss4.vo.PageHelperDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompDTO extends PageHelperDTO {
    private Integer cid;
    private String compName;
    private Date startTime;
    private Integer uid;
    private String imageUrl;
    private Boolean endingSign;
    private Boolean enterSign;
    private Integer compState;
    private JSONObject detailedInformation;
}
