package cn.net.powerinv.coss4.competition.mapper;

import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.entity.Comp;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Component;

@Component
public interface CompMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comp record);

    int insertSelective(Comp record);

    Comp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comp record);

    int updateByPrimaryKey(Comp record);

    /**
     * 查询竞赛列表
     * @param compDTO 查询条件
     * @return 查询结果
     */
    Page<Comp> queryCompetitionList(CompDTO compDTO);

    /**
     * 查询指定用户名下的竞赛列表
     * 用户只能更新自己名下的竞赛, root用户可以更新任何竞赛
     * @param compDTO 用户ID
     * @return 查询结果
     */
    Page<Comp> queryMyCompetitionList(CompDTO compDTO);

    /**
     * 更新竞赛信息
     * @param comp 新的竞赛信息
     * @return 更新结果
     */
    int updateOwnCompetition(Comp comp);

    /**
     * 删除竞赛信息
     * 用户只能删除自己名下的竞赛, root用户可以删除任何竞赛
     * @param comp 竞赛信息
     * @return 删除结果
     */
    int deleteOwnByPrimaryKey(Comp comp);

}