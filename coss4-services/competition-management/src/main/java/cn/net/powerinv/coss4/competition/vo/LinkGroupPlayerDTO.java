package cn.net.powerinv.coss4.competition.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkGroupPlayerDTO {
    private Integer id;
    private Integer groupId;
    private Integer playerId;
    private Integer userId;
    private String workName;
    private String imageUrl;
    private String state;
    private Date createTime;
    private Date updateTime;
}
