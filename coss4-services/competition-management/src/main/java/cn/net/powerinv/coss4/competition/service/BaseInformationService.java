package cn.net.powerinv.coss4.competition.service;

import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.entity.Comp;

import java.util.Map;

/**
 * 竞赛信息管理模块服务层接口
 * @author PengJQ
 * @date 2020-04-03
 */
public interface BaseInformationService {
    /**
     * 创建竞赛
     * @param comp 竞赛详细信息
     * @return 创建结果
     */
    Map<String, Object> createCompetition(Comp comp);

    /**
     * 根据ID查询竞赛信息
     * @param cid 竞赛ID
     * @return 竞赛详细信息
     */
    Map<String, Object> queryCompetition(Integer cid);

    /**
     * 分页查询竞赛列表
     * @param compDTO 分页条件
     * @return 竞赛列表
     */
    Map<String, Object> queryCompetitionList(CompDTO compDTO);

    /**
     * 分页查询指定用户持有的竞赛列表
     * @param compDTO 用户ID
     * @return 竞赛列表
     */
    Map<String, Object> queryMyCompetitionList(CompDTO compDTO);

    /**
     * 修改/更新竞赛详细信息
     * @param comp 新的竞赛详细信息
     * @return 更新结果
     */
    Map<String, Object> updateCompetition(Comp comp);

    /**
     * 发布竞赛
     * @param comp 竞赛信息
     * @return 更新结果
     */
    Map<String, Object> publicCompetition(Comp comp);

    /**
     * 取消发布竞赛
     * @param comp 竞赛信息
     * @return 更新结果
     */
    Map<String, Object> privateCompetition(Comp comp);

    /**
     * 删除竞赛
     * @param comp 竞赛ID
     * @return 删除结果
     */
    Map<String, Object> deleteCompetition(Comp comp);
}
