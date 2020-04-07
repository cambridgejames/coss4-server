package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.base.util.CompGroupTransformUtil;
import cn.net.powerinv.coss4.competition.entity.CompGroupExt;
import cn.net.powerinv.coss4.competition.service.CompetitionGroupService;
import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.competition.vo.CompGroupDTO;
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

@RestController
@RequestMapping("/group")
public class CompetitionGroupController {

    private CompetitionGroupService competitionGroupService;

    @Autowired
    public void setCompetitionGroupService(CompetitionGroupService competitionGroupService) {
        this.competitionGroupService = competitionGroupService;
    }

    /**
     * 创建竞赛分组
     *
     * @param compGroupDTO 竞赛分组详细信息
     * @return 插入结果
     */
    @ResponseBody
    @PostMapping("/createGroup")
    public Map<String, Object> createGroup(@RequestBody CompGroupDTO compGroupDTO) {
        if (ObjectUtils.isEmpty(compGroupDTO.getCompId()) || StringUtils.isEmpty(compGroupDTO.getGroupName())
                || ObjectUtils.isEmpty(compGroupDTO.getDetailRule())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        CompGroupExt compGroupExt = (CompGroupExt) CompGroupTransformUtil.vo2Entity(compGroupDTO,
                CompGroupTransformUtil.WITHOUT_GID);
        compGroupExt.setUid(user.getId());
        compGroupExt.setCreateTime(new Date());
        return competitionGroupService.createGroup(compGroupExt);
    }

    /**
     * 修改用户组信息
     *
     * @param compGroupDTO 新的竞赛分组信息
     * @return 修改结果
     */
    @ResponseBody
    @PostMapping("/updateGroup")
    public Map<String, Object> updateGroup(@RequestBody CompGroupDTO compGroupDTO) {
        if (ObjectUtils.isEmpty(compGroupDTO.getId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        CompGroupExt compGroupExt = (CompGroupExt) CompGroupTransformUtil.vo2Entity(compGroupDTO,
                CompGroupTransformUtil.WITH_GID);
        compGroupExt.setUid(user.getId());
        compGroupExt.setUpdateTime(new Date());
        return competitionGroupService.updateGroup(compGroupExt);
    }

    /**
     * 根据分组ID查询分组详细信息
     *
     * @param compGroupDTO 分组ID
     * @return 查询结果
     */
    @ResponseBody
    @GetMapping("/queryGroupById")
    public Map<String, Object> queryGroupById(@RequestBody CompGroupDTO compGroupDTO) {
        if (ObjectUtils.isEmpty(compGroupDTO.getId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        CompGroupExt compGroupExt = (CompGroupExt) CompGroupTransformUtil.vo2Entity(compGroupDTO,
                CompGroupTransformUtil.WITH_GID);
        compGroupExt.setUid(user.getId());
        return competitionGroupService.queryGroupById(compGroupExt);
    }

    /**
     * 查询当前用户指定竞赛下的竞赛列表
     *
     * @param compDTO 查询过滤条件
     * @return 查询结果列表
     */
    @ResponseBody
    @GetMapping("/queryGroupList")
    public Map<String, Object> queryGroupList(@RequestBody CompDTO compDTO) {
        if (ObjectUtils.isEmpty(compDTO.getCid())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        // 这里的uid指的是当前用户的ID，而不是竞赛所属用户的ID
        compDTO.setUid(user.getId());
        return competitionGroupService.queryGroupList(compDTO);
    }

    /**
     * 删除竞赛分组（同时删除关联表相关信息）
     *
     * @param compGroupDTO 竞赛分组ID
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping("/deleteGroup")
    public Map<String, Object> deleteGroup(@RequestBody CompGroupDTO compGroupDTO) {
        if (ObjectUtils.isEmpty(compGroupDTO.getId())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        CompGroupExt compGroupExt = (CompGroupExt) CompGroupTransformUtil.vo2Entity(compGroupDTO,
                CompGroupTransformUtil.WITH_GID);
        compGroupExt.setUid(user.getId());
        compGroupExt.setUpdateTime(new Date());
        return competitionGroupService.deleteGroup(compGroupExt);
    }

}
