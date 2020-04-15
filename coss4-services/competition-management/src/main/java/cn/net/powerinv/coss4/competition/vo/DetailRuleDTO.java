package cn.net.powerinv.coss4.competition.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailRuleDTO implements Serializable {
    private Integer id;
    private String compRubricName;
    private Integer minValue;
    private Integer maxValue;
    private Integer accuracy;
    private Double weight;
}
