package cn.net.powerinv.coss4.competition.entity;

import cn.net.powerinv.coss4.entity.UserPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlayerExt extends UserPlayer {
    private Integer uid;
}
