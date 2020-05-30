package cn.net.powerinv.coss4.competition.base.util;

import cn.net.powerinv.coss4.competition.vo.CompDTO;
import cn.net.powerinv.coss4.competition.vo.CompVO;
import cn.net.powerinv.coss4.entity.Comp;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库实体与VO类互转工具类
 * @author PengJQ
 * @date 2020-04-03
 */
public class CompTransformUtil {
    public static boolean WITH_CID = true;
    public static boolean WITHOUT_CID = false;

    /**
     * Comp实体类转VO
     * @param comp 数据库查询结果
     * @return 转换成的VO对象
     */
    public static CompVO entity2Vo(CompVO comp) {
        if (comp == null) {
            return null;
        }
        // JSONObject detail = JSON.parseObject(comp.getDetailedInformation());
        return new CompVO(comp.getId(), comp.getCompName(), comp.getStartTime(), comp.getUserId(), comp.getNickname(),
                comp.getImageUrl(), comp.getEndingSign(), comp.getEnterSign(), comp.getCompState(),
                comp.getDetailedInformation(), comp.getCreateTime(), comp.getUpdateTime());
    }

    /**
     * Comp实体类转VO
     * @param comp 数据库查询结果
     * @return 转换成的VO对象
     */
    public static CompVO entity2Vo(Comp comp) {
        if (comp == null) {
            return null;
        }
        // JSONObject detail = JSON.parseObject(comp.getDetailedInformation());
        return new CompVO(comp.getId(), comp.getCompName(), comp.getStartTime(), comp.getUserId(), null,
                comp.getImageUrl(), comp.getEndingSign(), comp.getEnterSign(), comp.getCompState(),
                comp.getDetailedInformation(), comp.getCreateTime(), comp.getUpdateTime());
    }

    /**
     * Comp实体类列表转VO列表
     * @param compList 数据库查询结果
     * @return 转换成的VO对象列表
     */
    public static List<CompVO> entity2VoList(List<CompVO> compList) {
        List<CompVO> compVOList = new ArrayList<>();
        if (compList != null && compList.size() != 0) {
            for (CompVO comp : compList) {
                compVOList.add(entity2Vo(comp));
            }
        }
        return compVOList;
    }

    /**
     * DTO转Comp实体类
     * @param compDTO 请求参数
     * @return 转换结果
     */
    public static Comp vo2Entity(CompDTO compDTO) {
        return vo2Entity(compDTO, CompTransformUtil.WITHOUT_CID);
    }

    /**
     * DTO转Comp实体类
     * @param compDTO 请求参数
     * @return 转换结果
     */
    public static Comp vo2Entity(CompDTO compDTO, boolean ifCid) {
        if (compDTO == null) {
            return null;
        }
        Comp comp = new Comp();
        if (ifCid) {
            comp.setId(compDTO.getCid());
        }
        comp.setCompName(compDTO.getCompName());
        comp.setStartTime(compDTO.getStartTime());
        comp.setImageUrl(compDTO.getImageUrl());
        comp.setEndingSign(compDTO.getEndingSign());
        comp.setCompState(compDTO.getCompState());
        comp.setDetailedInformation(compDTO.getDetailedInformation());
        return comp;
    }
}
