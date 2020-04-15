package cn.net.powerinv.coss4.competition.mapper;

import cn.net.powerinv.coss4.competition.entity.CompGroupExt;
import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.competition.vo.CompGroupDTO;
import cn.net.powerinv.coss4.entity.CompGroup;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Component;

@Component
public interface CompGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompGroup record);

    int insertSelective(CompGroup record);

    CompGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompGroup record);

    int updateByPrimaryKeyWithBLOBs(CompGroup record);

    int updateByPrimaryKey(CompGroup record);

    /**
     * 新建竞赛分组
     * @param compGroupExt 新分组的详细信息
     * @return 数据插入结果
     */
    int insertWithCheck(CompGroupExt compGroupExt);

    /**
     * 更新竞赛分组
     * @param compGroupExt 新的分组详细信息
     * @return 数据更新结果
     */
    int updateWithCheck(CompGroupExt compGroupExt);

    /**
     * 查询分组信息
     * @param compGroupExt 分组ID
     * @return 分组详细信息
     */
    CompGroup selectWithCheck(CompGroupExt compGroupExt);

    /**
     * 查询分组信息
     * @param compDTO 分组ID
     * @return 分组详细信息
     */
    Page<CompGroup> queryListWithCheck(CompDTO compDTO);

    /**
     * 删除分组信息
     * @param compGroupExt 分组ID
     * @return 删除结果
     */
    int deleteGroup(CompGroupExt compGroupExt);
}