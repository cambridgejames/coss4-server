package cn.net.powerinv.coss4.competition.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompGroupVO implements Serializable {
    private Integer id;
    private String groupName;
    private Integer compId;
    private Integer priority;
    private JSONArray detailRule;
    private Date createTime;
    private Date updateTime;
}
