package cn.net.powerinv.coss4.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer uid;
    private String username;
    private String password;
    private Boolean sex;
    private String nickname;
    private String avatar;
    private String phone;
    private String eMail;
}
