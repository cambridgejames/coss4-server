package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.competition.service.CompetitionGroupService;
import cn.net.powerinv.coss4.entity.CompGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @param compGroup 竞赛分组详细信息
     * @return 插入结果
     */
    @ResponseBody
    @PostMapping("/createGroup")
    public Map<String, Object> createGroup(@RequestBody CompGroup compGroup) {
        return competitionGroupService.createGroup(compGroup);
    }

    /**
     * 修改用户组信息
     *
     * @param compGroup 新的竞赛分组信息
     * @return 修改结果
     */
    @ResponseBody
    @PostMapping("/updateGroup")
    public Map<String, Object> updateGroup(@RequestBody CompGroup compGroup) {
        return competitionGroupService.updateGroup(compGroup);
    }

    /**
     * 根据分组ID查询分组详细信息
     *
     * @param id 分组ID
     * @return 查询结果
     */
    @ResponseBody
    @GetMapping("/queryGroupById")
    public Map<String, Object> queryGroupById(@RequestBody Integer id) {
        return competitionGroupService.queryGroupById(id);
    }

    /**
     * 查询当前用户指定竞赛下的竞赛列表
     *
     * @param compGroup 查询过滤条件
     * @return 查询结果列表
     */
    @ResponseBody
    @GetMapping("/queryGroupList")
    public Map<String, Object> queryGroupList(@RequestBody CompGroup compGroup) {
        return competitionGroupService.queryGroupList(compGroup);
    }

    /**
     * 删除竞赛分组（同时删除关联表相关信息）
     *
     * @param id 竞赛分组ID
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping("/deleteGroup")
    public Map<String, Object> deleteGroup(@RequestBody Integer id) {
        return competitionGroupService.deleteGroup(id);
    }

}
