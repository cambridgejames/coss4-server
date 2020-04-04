package cn.net.powerinv.coss4.competition.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.base.util.CompTransformUtil;
import cn.net.powerinv.coss4.competition.mapper.CompMapper;
import cn.net.powerinv.coss4.competition.service.BaseInformationService;
import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.competition.vo.CompVO;
import cn.net.powerinv.coss4.entity.Comp;
import cn.net.powerinv.coss4.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 竞赛信息管理模块服务层实现类
 * @author PengJQ
 * @date 2020-04-03
 */
@Slf4j
@Service
public class BaseInformationServiceImpl implements BaseInformationService {

    private CompMapper compMapper;

    @Autowired
    public void setCompMapper(CompMapper compMapper) {
        this.compMapper = compMapper;
    }

    /**
     * 创建竞赛
     *
     * @param comp 竞赛详细信息
     * @return 创建结果
     */
    @Override
    public Map<String, Object> createCompetition(Comp comp) {
        // 先查询用户信息
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }

        comp.setUserId(user.getId());
        if (compMapper.insertSelective(comp) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.INSERT_FAILED);
        }
        return CommonResultUtil.returnTrue();
    }

    /**
     * 根据ID查询竞赛信息
     *
     * @param cid 竞赛ID
     * @return 竞赛详细信息
     */
    @Override
    public Map<String, Object> queryCompetition(Integer cid) {
        Comp comp = compMapper.selectByPrimaryKey(cid);
        if (comp == null) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        try {
            CompVO compVO = CompTransformUtil.entity2Vo(comp);
            return CommonResultUtil.returnTrue(compVO);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_TYPE_ILLEGAL);
        }
    }

    /**
     * 分页查询竞赛列表
     *
     * @param compDTO 查询条件
     * @return 竞赛列表
     */
    @Override
    public Map<String, Object> queryCompetitionList(CompDTO compDTO) {
        PageHelper.startPage(compDTO.getPageNumber(), compDTO.getPageSize());
        Page<Comp> compListPage = compMapper.queryCompetitionList(compDTO);
        PageInfo<Comp> pageInfo = new PageInfo<>(compListPage);
        List<Comp> compList = pageInfo.getList();
        try {
            List<CompVO> compVOList = CompTransformUtil.entity2VoList(compList);
            return CommonResultUtil.returnTrue(pageInfo.getTotal(), compDTO.getPageNumber(), compVOList);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_TYPE_ILLEGAL);
        }
    }

    /**
     * 分页查询指定用户持有的竞赛列表
     *
     * @param compDTO 用户ID
     * @return 竞赛列表
     */
    @Override
    public Map<String, Object> queryMyCompetitionList(CompDTO compDTO) {
        PageHelper.startPage(compDTO.getPageNumber(), compDTO.getPageSize());
        Page<Comp> compListPage = compMapper.queryMyCompetitionList(compDTO);
        PageInfo<Comp> pageInfo = new PageInfo<>(compListPage);
        List<Comp> compList = pageInfo.getList();
        try {
            List<CompVO> compVOList = CompTransformUtil.entity2VoList(compList);
            return CommonResultUtil.returnTrue(pageInfo.getTotal(), compDTO.getPageNumber(), compVOList);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_TYPE_ILLEGAL);
        }
    }

    /**
     * 修改/更新竞赛详细信息
     *
     * @param comp 新的竞赛详细信息
     * @return 更新结果
     */
    @Override
    public Map<String, Object> updateCompetition(Comp comp) {
        if (compMapper.updateOwnCompetition(comp) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        return CommonResultUtil.returnTrue();
    }

    /**
     * 发布竞赛
     *
     * @param comp 竞赛信息
     * @return 更新结果
     */
    @Override
    public Map<String, Object> publicCompetition(Comp comp) {
        comp.setCompState(10);
        if (compMapper.updateOwnCompetition(comp) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.PERMISSION_DENIED);
        }
        return CommonResultUtil.returnTrue();
    }

    /**
     * 取消发布竞赛
     *
     * @param comp 竞赛信息
     * @return 更新结果
     */
    @Override
    public Map<String, Object> privateCompetition(Comp comp) {
        comp.setCompState(20);
        // TODO: 已经开始或已经有人报名（关联账户）的竞赛不能取消发布
        return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
    }

    /**
     * 删除竞赛
     *
     * @param comp 竞赛ID
     * @return 删除结果
     */
    @Override
    public Map<String, Object> deleteCompetition(Comp comp) {
        if (compMapper.deleteOwnByPrimaryKey(comp) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        log.info("Deleted competition -> {}", comp);
        return CommonResultUtil.returnTrue();
    }
}
