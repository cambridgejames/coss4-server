package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.base.util.CompTransformUtil;
import cn.net.powerinv.coss4.competition.service.BaseInformationService;
import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.entity.Comp;
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

/**
 * 竞赛信息管理模块控制层
 * @author PengJQ
 * @date 2020-04-03
 */
@RestController
@RequestMapping("/base")
public class BaseInformationController {

    private BaseInformationService baseInformationService;

    @Autowired
    public void setBaseInformationService(BaseInformationService baseInformationService) {
        this.baseInformationService = baseInformationService;
    }

    /**
     * 创建竞赛
     * @param compDTO 竞赛详细信息
     * @return 创建结果
     */
    @ResponseBody
    @PostMapping("/createCompetition")
    public Map<String, Object> createCompetition(@RequestBody CompDTO compDTO) {
        if (ObjectUtils.isEmpty(compDTO.getCompState())) {
            compDTO.setCompState(20);
        } else if (compDTO.getCompState() != 10 && compDTO.getCompState() != 20) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_ILLEGAL);
        }
        if (StringUtils.isEmpty(compDTO.getCompName()) || ObjectUtils.isEmpty(compDTO.getStartTime())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        Comp comp = CompTransformUtil.vo2Entity(compDTO);
        comp.setCreateTime(new Date());
        return baseInformationService.createCompetition(comp);
    }

    /**
     * 根据ID查询竞赛信息
     * @param compDTO 竞赛ID
     * @return 竞赛详细信息
     */
    @ResponseBody
    @GetMapping("/queryCompetition")
    public Map<String, Object> queryCompetition(@RequestBody CompDTO compDTO) {
        if (ObjectUtils.isEmpty(compDTO.getCid())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        return baseInformationService.queryCompetition(compDTO.getCid());
    }

    /**
     * 分页查询竞赛列表
     * @param compDTO 查询条件
     * @return 竞赛列表
     */
    @ResponseBody
    @GetMapping("/queryCompetitionList")
    public Map<String, Object> queryCompetitionList(@RequestBody CompDTO compDTO) {
        return baseInformationService.queryCompetitionList(compDTO);
    }

    /**
     * 分页查询指定用户持有的竞赛列表
     * @param compDTO 用户ID
     * @return 竞赛列表
     */
    @ResponseBody
    @GetMapping("/queryMyCompetitionList")
    public Map<String, Object> queryMyCompetitionList(@RequestBody CompDTO compDTO) {
        if (ObjectUtils.isEmpty(compDTO.getUid())) {
            // 如果没有传入uid则查询当前用户名下的列表
            HttpServletRequest request = ((ServletRequestAttributes) Objects
                    .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
            }
            compDTO.setUid(user.getId());
        }
        return baseInformationService.queryMyCompetitionList(compDTO);
    }

    /**
     * 修改/更新竞赛详细信息
     * @param compDTO 新的竞赛详细信息
     * @return 更新结果
     */
    @ResponseBody
    @PostMapping("/updateCompetition")
    public Map<String, Object> updateCompetition(@RequestBody CompDTO compDTO) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(compDTO.getCid())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        Comp comp = CompTransformUtil.vo2Entity(compDTO, CompTransformUtil.WITH_CID);
        // 需要注意的是这里的 comp.userId 中存的并不是竞赛归属者的ID，而是当前用户的ID
        comp.setUserId(user.getId());
        comp.setCompState(null);    // 在这个方法中禁止更新状态信息
        comp.setUpdateTime(new Date());
        return baseInformationService.updateCompetition(comp);
    }

    /**
     * 发布/取消发布竞赛
     * @param compDTO 竞赛信息
     * @return 更新结果
     */
    @ResponseBody
    @PostMapping("/updateCompetitionState")
    public Map<String, Object> updateCompetitionState(@RequestBody CompDTO compDTO) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(compDTO.getCid()) || ObjectUtils.isEmpty(compDTO.getCompState())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        Comp comp = new Comp();
        // 需要注意的是这里的 comp.userId 中存的并不是竞赛归属者的ID，而是当前用户的ID
        comp.setId(compDTO.getCid());
        comp.setUserId(user.getId());
        comp.setUpdateTime(new Date());
        if (compDTO.getCompState() == 10) {
            return baseInformationService.publicCompetition(comp);
        } else if (compDTO.getCompState() == 20) {
            return baseInformationService.privateCompetition(comp);
        } else {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_ILLEGAL);
        }
    }

    /**
     * 删除竞赛
     * @param compDTO 竞赛ID
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping("/deleteCompetition")
    public Map<String, Object> deleteCompetition(@RequestBody CompDTO compDTO) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return CommonResultUtil.returnFalse(MessageCode.TOKEN_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(compDTO.getCid())) {
            return CommonResultUtil.returnFalse(MessageCode.PARAMETERS_NOT_ENOUGH);
        }
        Comp comp = CompTransformUtil.vo2Entity(compDTO, CompTransformUtil.WITH_CID);
        // 需要注意的是这里的 comp.userId 中存的并不是竞赛归属者的ID，而是当前用户的ID
        comp.setUserId(user.getId());
        comp.setUpdateTime(new Date());
        return baseInformationService.deleteCompetition(comp);
    }
}
