package cn.net.powerinv.coss4.competition.service;

import cn.net.powerinv.coss4.competition.vo.LinkGroupPlayerDTO;

import java.util.Map;

public interface PlayerDistributionService {

    /**
     * 为选手添加分组
     * @param linkGroupPlayerDTO 分组信息
     * @return 添加结果
     */
    Map<String, Object> addGroupForPlayer(LinkGroupPlayerDTO linkGroupPlayerDTO);
}
