package cn.net.powerinv.coss4.competition.service.impl;

import cn.net.powerinv.coss4.competition.mapper.UserPlayerMapper;
import cn.net.powerinv.coss4.competition.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 竞赛选手管理模块服务层实现类
 * @author PengJQ
 * @date 2020-04-08
 */
@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private UserPlayerMapper userPlayerMapper;

    @Autowired
    public void setUserPlayerMapper(UserPlayerMapper userPlayerMapper) {
        this.userPlayerMapper = userPlayerMapper;
    }
}
