package cn.net.powerinv.coss4.competition.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompVO {
    private Integer id;
    private String compName;
    private Date startTime;
    private Integer userId;
    private String imageUrl;
    private Boolean endingSign;
    private Boolean enterSign;
    private Integer compState;
    private JSONObject detailedInformation;
    private Date createTime;
    private Date updateTime;
}
