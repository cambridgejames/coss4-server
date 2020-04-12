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

    /**
     * 将制定选手从指定选手分组中移除
     * @param linkGroupPlayerDTO 分组信息
     * @return 删除结果
     */
    Map<String, Object> removeGroup(LinkGroupPlayerDTO linkGroupPlayerDTO);

    /**
     * 修改选手作品信息
     * @param linkGroupPlayerDTO 作品信息
     * @return 更新结果
     */
    Map<String, Object> updateWork(LinkGroupPlayerDTO linkGroupPlayerDTO);
}
