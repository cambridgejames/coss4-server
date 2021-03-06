package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.service.PlayerDistributionService;
import cn.net.powerinv.coss4.competition.vo.LinkGroupPlayerDTO;
import cn.net.powerinv.coss4.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/distribute")
public class PlayerDistributionController {

    private PlayerDistributionService playerDistributionService;

    @Autowired
    public void setPlayerDistributionService(PlayerDistributionService playerDistributionService) {
        this.playerDistributionService = playerDistributionService;
    }

    /**
     * 为选手添加分组
     *
     * @param linkGroupPlayerDTO 分组信息
     * @return 添加结果
     */
    @ResponseBody
    @PostMapping("/addGroupForPlayer")
    public Map<String, Object> addGroupForPlayer(LinkGroupPlayerDTO linkGroupPlayerDTO) {
        if (ObjectUtils.isEmpty(linkGroupPlayerDTO.getGroupId())
                || ObjectUtils.isEmpty(linkGroupPlayerDTO.getPlayerId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        linkGroupPlayerDTO.setUserId(user.getId());
        linkGroupPlayerDTO.setCreateTime(new Date());
        return playerDistributionService.addGroupForPlayer(linkGroupPlayerDTO);
    }

    /**
     * 将制定选手从指定选手分组中移除
     *
     * @param linkGroupPlayerDTO 分组信息
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping("/removeGroup")
    public Map<String, Object> removeGroup(LinkGroupPlayerDTO linkGroupPlayerDTO) {
        if (ObjectUtils.isEmpty(linkGroupPlayerDTO.getGroupId())
                || ObjectUtils.isEmpty(linkGroupPlayerDTO.getPlayerId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        linkGroupPlayerDTO.setUserId(user.getId());
        linkGroupPlayerDTO.setUpdateTime(new Date());
        return playerDistributionService.removeGroup(linkGroupPlayerDTO);
    }

    /**
     * 修改选手作品信息
     *
     * @param linkGroupPlayerDTO 作品信息
     * @return 更新结果
     */
    @ResponseBody
    @PostMapping("/updateWork")
    public Map<String, Object> updateWork(LinkGroupPlayerDTO linkGroupPlayerDTO) {
        if (ObjectUtils.isEmpty(linkGroupPlayerDTO.getGroupId())
                || ObjectUtils.isEmpty(linkGroupPlayerDTO.getPlayerId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        linkGroupPlayerDTO.setUserId(user.getId());
        linkGroupPlayerDTO.setUpdateTime(new Date());
        return playerDistributionService.updateWork(linkGroupPlayerDTO);
    }
}
