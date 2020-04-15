package cn.net.powerinv.coss4.competition.vo;

import cn.net.powerinv.coss4.vo.PageHelperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlayerDTO extends PageHelperDTO {
    private Integer id;
    private String userName;
    private String imageUrl;
    private Integer compId;
    private Integer userId;
    private Integer groupId;

    @Override
    public String toString() {
        return "UserPlayerDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", compId=" + compId +
                ", userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}
