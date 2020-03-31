package cn.net.powerinv.coss4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Integer id;
    private String username;
    private Integer sex;
    private String nickname;
    private String avatar;
    private String phone;
    private String eMail;
    private Date createTime;
    private Date updateTime;
}
