package cn.net.powerinv.coss4.competition.service;

import cn.net.powerinv.coss4.competition.entity.CompGroupExt;
import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.competition.vo.CompGroupDTO;
import cn.net.powerinv.coss4.entity.CompGroup;

import java.util.Map;

/**
 * 竞赛信息服务 - 竞赛分组管理模块
 * @author PengJQ
 * @date 2020-04-07
 */
public interface CompetitionGroupService {

    /**
     * 创建竞赛分组
     * @param compGroupExt 竞赛分组详细信息
     * @return 插入结果
     */
    Map<String, Object> createGroup(CompGroupExt compGroupExt);

    /**
     * 修改用户组信息
     * @param compGroupExt 新的竞赛分组信息
     * @return 修改结果
     */
    Map<String, Object> updateGroup(CompGroupExt compGroupExt);

    /**
     * 根据分组ID查询分组详细信息
     * @param compGroupExt 分组ID
     * @return 查询结果
     */
    Map<String, Object> queryGroupById(CompGroupExt compGroupExt);

    /**
     * 查询当前用户指定竞赛下的竞赛列表
     * @param compDTO 查询过滤条件
     * @return 查询结果列表
     */
    Map<String, Object> queryGroupList(CompDTO compDTO);

    /**
     * 删除竞赛分组（同时删除关联表相关信息）
     * @param compGroupExt 竞赛分组ID
     * @return 删除结果
     */
    Map<String, Object> deleteGroup(CompGroupExt compGroupExt);
}
