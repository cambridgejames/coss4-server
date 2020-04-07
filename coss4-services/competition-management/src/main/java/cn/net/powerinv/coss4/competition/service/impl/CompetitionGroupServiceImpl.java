package cn.net.powerinv.coss4.competition.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.base.util.CompGroupTransformUtil;
import cn.net.powerinv.coss4.competition.base.util.CompTransformUtil;
import cn.net.powerinv.coss4.competition.entity.CompGroupExt;
import cn.net.powerinv.coss4.competition.mapper.CompGroupMapper;
import cn.net.powerinv.coss4.competition.mapper.LinkGroupPlayerMapper;
import cn.net.powerinv.coss4.competition.service.CompetitionGroupService;
import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.competition.vo.CompGroupDTO;
import cn.net.powerinv.coss4.competition.vo.CompGroupVO;
import cn.net.powerinv.coss4.competition.vo.CompVO;
import cn.net.powerinv.coss4.entity.Comp;
import cn.net.powerinv.coss4.entity.CompGroup;
import cn.net.powerinv.coss4.entity.LinkGroupPlayer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CompetitionGroupServiceImpl implements CompetitionGroupService {

    private CompGroupMapper compGroupMapper;
    private LinkGroupPlayerMapper linkGroupPlayerMapper;

    @Autowired
    public void setCompGroupMapper(CompGroupMapper compGroupMapper) {
        this.compGroupMapper = compGroupMapper;
    }

    @Autowired
    public void setLinkGroupPlayerMapper(LinkGroupPlayerMapper linkGroupPlayerMapper) {
        this.linkGroupPlayerMapper = linkGroupPlayerMapper;
    }

    /**
     * 创建竞赛分组
     *
     * @param compGroupExt 竞赛分组详细信息
     * @return 插入结果
     */
    @Override
    public Map<String, Object> createGroup(CompGroupExt compGroupExt) {
        if (compGroupMapper.insertWithCheck(compGroupExt) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        return CommonResultUtil.returnTrue(CompGroupTransformUtil.entity2Vo((CompGroup) compGroupExt));
    }

    /**
     * 修改用户组信息
     *
     * @param compGroupExt 新的竞赛分组信息
     * @return 修改结果
     */
    @Override
    public Map<String, Object> updateGroup(CompGroupExt compGroupExt) {
        if (compGroupMapper.updateWithCheck(compGroupExt) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        return CommonResultUtil.returnTrue();
    }

    /**
     * 根据分组ID查询分组详细信息
     *
     * @param compGroupExt 分组ID
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryGroupById(CompGroupExt compGroupExt) {
        CompGroup compGroup = compGroupMapper.selectWithCheck(compGroupExt);
        if (compGroup == null) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        return CommonResultUtil.returnTrue(CompGroupTransformUtil.entity2Vo(compGroup));
    }

    /**
     * 查询当前用户指定竞赛下的竞赛列表
     *
     * @param compDTO 查询过滤条件
     * @return 查询结果列表
     */
    @Override
    public Map<String, Object> queryGroupList(CompDTO compDTO) {
        PageHelper.startPage(compDTO.getPageNumber(), compDTO.getPageSize());
        Page<CompGroup> compGroupListPage = compGroupMapper.queryListWithCheck(compDTO);
        PageInfo<CompGroup> pageInfo = new PageInfo<>(compGroupListPage);
        List<CompGroup> compGroupList = pageInfo.getList();
        List<CompGroupVO> compGroupVOList = CompGroupTransformUtil.entity2VoList(compGroupList);
        return CommonResultUtil.returnTrue(pageInfo.getTotal(), compDTO.getPageNumber(), compGroupVOList);
    }

    /**
     * 删除竞赛分组（同时删除关联表相关信息）
     *
     * @param compGroupExt 竞赛分组ID
     * @return 删除结果
     */
    @Override
    @Transactional
    public Map<String, Object> deleteGroup(CompGroupExt compGroupExt) {
        if (compGroupMapper.deleteGroup(compGroupExt) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        // 如果能执行到这里说明uid一定校验通过了，所以不需要再校验
        LinkGroupPlayer linkGroupPlayer = new LinkGroupPlayer();
        linkGroupPlayer.setGroupId(compGroupExt.getId());
        linkGroupPlayer.setUpdateTime(new Date());
        linkGroupPlayerMapper.deleteByGroupId(linkGroupPlayer);
        return CommonResultUtil.returnTrue();
    }
}
