package cn.net.powerinv.coss4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {
    private Integer uid;
    private String oldPassword;
    private String newPassword;
    private Date updateTime;
}
