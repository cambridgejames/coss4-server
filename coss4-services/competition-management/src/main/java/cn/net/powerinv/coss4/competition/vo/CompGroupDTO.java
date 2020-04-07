package cn.net.powerinv.coss4.competition.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompGroupDTO implements Serializable {
    private Integer id;
    private String groupName;
    private Integer compId;
    private Integer priority;
    private List<DetailRuleDTO> detailRule;
}
