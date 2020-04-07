package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.competition.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 竞赛选手管理模块控制层
 * @author PengJQ
 * @date 2020-04-08
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
