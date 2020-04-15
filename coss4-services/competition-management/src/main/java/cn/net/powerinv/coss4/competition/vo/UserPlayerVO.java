package cn.net.powerinv.coss4.competition.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlayerVO {
    private Integer id;
    private String userName;
    private String imageUrl;
    private Integer compId;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
}
