package cn.net.powerinv.coss4.competition.base.util;

import cn.net.powerinv.coss4.competition.entity.CompGroupExt;
import cn.net.powerinv.coss4.competition.vo.CompGroupDTO;
import cn.net.powerinv.coss4.competition.vo.CompGroupVO;
import cn.net.powerinv.coss4.entity.CompGroup;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库实体与VO类互转工具类
 * @author PengJQ
 * @date 2020-04-07
 */
public class CompGroupTransformUtil {
    public static boolean WITH_GID = true;
    public static boolean WITHOUT_GID = false;

    /**
     * CompGroup实体类转VO
     * @param compGroup 数据库查询结果
     * @return 转换成的VO对象
     */
    public static CompGroupVO entity2Vo(CompGroup compGroup) {
        if (compGroup == null) {
            return null;
        }
        // 只要是正常的存入数据库，取出来就不会有任何问题，不需要throw
        JSONArray detailRule = JSONArray.parseArray(compGroup.getDetailRule());
        return new CompGroupVO(compGroup.getId(), compGroup.getGroupName(), compGroup.getCompId(),
                compGroup.getPriority(), detailRule, compGroup.getCreateTime(), compGroup.getUpdateTime());
    }

    /**
     * Comp实体类列表转VO列表
     * @param compGroupList 数据库查询结果
     * @return 转换成的VO对象列表
     */
    public static List<CompGroupVO> entity2VoList(List<CompGroup> compGroupList) {
        List<CompGroupVO> compGroupVOList = new ArrayList<>();
        if (compGroupList != null && compGroupList.size() != 0) {
            for (CompGroup compGroup : compGroupList) {
                compGroupVOList.add(entity2Vo(compGroup));
            }
        }
        return compGroupVOList;
    }

    /**
     * DTO转Comp实体类
     * @param compGroupDTO 请求参数
     * @return 转换结果
     */
    public static CompGroup vo2Entity(CompGroupDTO compGroupDTO) {
        return vo2Entity(compGroupDTO, CompGroupTransformUtil.WITHOUT_GID);
    }

    /**
     * DTO转Comp实体类
     * @param compGroupDTO 请求参数
     * @return 转换结果
     */
    public static CompGroup vo2Entity(CompGroupDTO compGroupDTO, boolean ifGid) {
        if (compGroupDTO == null) {
            return null;
        }
        CompGroup compGroup = new CompGroupExt();
        if (ifGid) {
            compGroup.setId(compGroupDTO.getId());
        }
        compGroup.setGroupName(compGroupDTO.getGroupName());
        compGroup.setPriority(compGroupDTO.getPriority());
        compGroup.setCompId(compGroupDTO.getCompId());
        if (compGroupDTO.getDetailRule() != null) {
            compGroup.setDetailRule(((JSONArray) JSONArray.toJSON(compGroupDTO.getDetailRule())).toString());
        }
        return compGroup;
    }
}
