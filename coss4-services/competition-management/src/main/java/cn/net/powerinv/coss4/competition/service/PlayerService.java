package cn.net.powerinv.coss4.competition.service;

import cn.net.powerinv.coss4.competition.entity.UserPlayerExt;
import cn.net.powerinv.coss4.competition.vo.UserPlayerDTO;

import java.util.Map;

/**
 * 竞赛选手管理模块服务层接口
 * @author PengJQ
 * @date 2020-04-08
 */
public interface PlayerService {

    /**
     * 创建新的参赛选手
     *
     * @param userPlayerExt 选手信息
     * @return 插入结果
     */
    Map<String, Object> createPlayer(UserPlayerExt userPlayerExt);

    /**
     * 更新选手信息
     *
     * @param userPlayerExt 新的选手信息
     * @return 更新结果
     */
    Map<String, Object> updatePlayer(UserPlayerExt userPlayerExt);

    /**
     * 查询选手信息
     *
     * @param userPlayerExt 选手ID
     * @return 查询结果
     */
    Map<String, Object> queryPlayerById(UserPlayerExt userPlayerExt);

    /**
     * 查询选手信息列表
     * @param userPlayerDTO 查询条件
     * @return 查询结果
     */
    Map<String, Object> queryPlayerList(UserPlayerDTO userPlayerDTO);

    /**
     * 删除用户
     * @param userPlayerExt 用户ID
     * @return 删除结果
     */
    Map<String, Object> deletePlayer(UserPlayerExt userPlayerExt);
}
