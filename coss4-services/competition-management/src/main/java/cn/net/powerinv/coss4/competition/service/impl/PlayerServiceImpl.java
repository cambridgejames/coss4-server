package cn.net.powerinv.coss4.competition.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.base.util.UserPlayerTransformUtil;
import cn.net.powerinv.coss4.competition.entity.UserPlayerExt;
import cn.net.powerinv.coss4.competition.mapper.LinkGroupPlayerMapper;
import cn.net.powerinv.coss4.competition.mapper.UserPlayerMapper;
import cn.net.powerinv.coss4.competition.service.PlayerService;
import cn.net.powerinv.coss4.competition.vo.UserPlayerDTO;
import cn.net.powerinv.coss4.competition.vo.UserPlayerVO;
import cn.net.powerinv.coss4.entity.LinkGroupPlayer;
import cn.net.powerinv.coss4.entity.UserPlayer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 竞赛选手管理模块服务层实现类
 * @author PengJQ
 * @date 2020-04-08
 */
@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private UserPlayerMapper userPlayerMapper;
    private LinkGroupPlayerMapper linkGroupPlayerMapper;

    @Autowired
    public void setUserPlayerMapper(UserPlayerMapper userPlayerMapper) {
        this.userPlayerMapper = userPlayerMapper;
    }

    @Autowired
    public void setLinkGroupPlayerMapper(LinkGroupPlayerMapper linkGroupPlayerMapper) {
        this.linkGroupPlayerMapper = linkGroupPlayerMapper;
    }

    /**
     * 创建新的参赛选手
     *
     * @param userPlayerExt 选手信息
     * @return 插入结果
     */
    @Override
    public Map<String, Object> createPlayer(UserPlayerExt userPlayerExt) {
        if (userPlayerMapper.insertWithCheck(userPlayerExt) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        return CommonResultUtil.returnTrue(UserPlayerTransformUtil.entity2Vo(userPlayerExt));
    }

    /**
     * 更新选手信息
     *
     * @param userPlayerExt 新的选手信息
     * @return 更新结果
     */
    @Override
    public Map<String, Object> updatePlayer(UserPlayerExt userPlayerExt) {
        if (userPlayerMapper.updateWithCheck(userPlayerExt) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        return CommonResultUtil.returnTrue();
    }

    /**
     * 查询选手信息
     *
     * @param userPlayerExt 选手ID
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryPlayerById(UserPlayerExt userPlayerExt) {
        UserPlayer userPlayer = userPlayerMapper.selectWithCheck(userPlayerExt);
        if (userPlayer == null) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        return CommonResultUtil.returnTrue(UserPlayerTransformUtil.entity2Vo(userPlayer));
    }

    /**
     * 查询选手信息列表
     *
     * @param userPlayerDTO 查询条件
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryPlayerList(UserPlayerDTO userPlayerDTO) {
        PageHelper.startPage(userPlayerDTO.getPageNumber(), userPlayerDTO.getPageSize());
        Page<UserPlayer> userPlayerListPage = userPlayerMapper.queryPlayerList(userPlayerDTO);
        PageInfo<UserPlayer> pageInfo = new PageInfo<>(userPlayerListPage);
        List<UserPlayer> userPlayerList = pageInfo.getList();
        List<UserPlayerVO> userPlayerVOList = UserPlayerTransformUtil.entity2VoList(userPlayerList);
        return CommonResultUtil.returnTrue(pageInfo.getTotal(), userPlayerDTO.getPageNumber(), userPlayerVOList);
    }

    /**
     * 删除用户
     *
     * @param userPlayerExt 用户ID
     * @return 删除结果
     */
    @Override
    @Transactional
    public Map<String, Object> deletePlayer(UserPlayerExt userPlayerExt) {
        if (userPlayerMapper.deleteWithCheck(userPlayerExt) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        // 如果能执行到这里说明uid一定校验通过了，所以不需要再校验
        LinkGroupPlayer linkGroupPlayer = new LinkGroupPlayer();
        linkGroupPlayer.setPlayerId(userPlayerExt.getId());
        linkGroupPlayerMapper.deleteByPlayerId(linkGroupPlayer);
        return CommonResultUtil.returnTrue();
    }

}
