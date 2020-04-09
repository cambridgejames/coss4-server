package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.base.util.UserPlayerTransformUtil;
import cn.net.powerinv.coss4.competition.entity.UserPlayerExt;
import cn.net.powerinv.coss4.competition.service.PlayerService;
import cn.net.powerinv.coss4.competition.vo.UserPlayerDTO;
import cn.net.powerinv.coss4.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 竞赛选手管理模块控制层
 * @author PengJQ
 * @date 2020-04-08
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * 创建新的参赛选手
     * @param userPlayerDTO 选手信息
     * @return 插入结果
     */
    @ResponseBody
    @PostMapping("/createPlayer")
    public Map<String, Object> createPlayer(@RequestBody UserPlayerDTO userPlayerDTO) {
        if (StringUtils.isEmpty(userPlayerDTO.getUserName()) || ObjectUtils.isEmpty(userPlayerDTO.getCompId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        String password = UUID.nameUUIDFromBytes(userPlayerDTO.toString().getBytes()).toString().substring(0, 8);
        UserPlayerExt userPlayerExt = (UserPlayerExt) UserPlayerTransformUtil.vo2Entity(userPlayerDTO,
                UserPlayerTransformUtil.WITHOUT_PID);
        userPlayerExt.setPassword(password);
        userPlayerExt.setUserId(null);
        userPlayerExt.setUid(user.getId());
        userPlayerExt.setCreateTime(new Date());
        return playerService.createPlayer(userPlayerExt);
    }

    /**
     * 更新选手信息
     * @param userPlayerDTO 新的选手信息
     * @return 更新结果
     */
    @ResponseBody
    @PostMapping("/updatePlayer")
    public Map<String, Object> updatePlayer(@RequestBody UserPlayerDTO userPlayerDTO) {
        if (ObjectUtils.isEmpty(userPlayerDTO.getId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        UserPlayerExt userPlayerExt = (UserPlayerExt) UserPlayerTransformUtil.vo2Entity(userPlayerDTO,
                UserPlayerTransformUtil.WITH_PID);
        userPlayerExt.setUserId(null);  // 此接口中禁止更新用户绑定信息
        userPlayerExt.setUid(user.getId());
        userPlayerExt.setUpdateTime(new Date());
        return playerService.updatePlayer(userPlayerExt);
    }

    /**
     * 查询选手信息
     * @param userPlayerDTO 选手ID
     * @return 查询结果
     */
    @ResponseBody
    @GetMapping("/queryPlayerById")
    public Map<String, Object> queryPlayerById(@RequestBody UserPlayerDTO userPlayerDTO) {
        if (ObjectUtils.isEmpty(userPlayerDTO.getId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        UserPlayerExt userPlayerExt = (UserPlayerExt) UserPlayerTransformUtil.vo2Entity(userPlayerDTO,
                UserPlayerTransformUtil.WITH_PID);
        userPlayerExt.setUid(user.getId());
        return playerService.queryPlayerById(userPlayerExt);
    }

    /**
     * 查询选手信息列表
     * @param userPlayerDTO 查询条件
     * @return 查询结果
     */
    @ResponseBody
    @GetMapping("/queryPlayerList")
    public Map<String, Object> queryPlayerList(@RequestBody UserPlayerDTO userPlayerDTO) {
        if (ObjectUtils.isEmpty(userPlayerDTO.getCompId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        // 这里的UID是当前用户的UID，不是被关联用户的
        userPlayerDTO.setUserId(user.getId());
        return playerService.queryPlayerList(userPlayerDTO);
    }

    /**
     * 删除用户
     * @param userPlayerDTO 用户ID
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping("/deletePlayer")
    public Map<String, Object> deletePlayer(@RequestBody UserPlayerDTO userPlayerDTO) {
        if (ObjectUtils.isEmpty(userPlayerDTO.getId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        UserPlayerExt userPlayerExt = (UserPlayerExt) UserPlayerTransformUtil.vo2Entity(userPlayerDTO,
                UserPlayerTransformUtil.WITH_PID);
        userPlayerExt.setUid(user.getId());
        return playerService.deletePlayer(userPlayerExt);
    }
}
