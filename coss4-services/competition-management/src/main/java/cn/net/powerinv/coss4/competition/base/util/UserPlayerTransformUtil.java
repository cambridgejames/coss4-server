package cn.net.powerinv.coss4.competition.base.util;

import cn.net.powerinv.coss4.competition.entity.UserPlayerExt;
import cn.net.powerinv.coss4.competition.vo.UserPlayerDTO;
import cn.net.powerinv.coss4.competition.vo.UserPlayerVO;
import cn.net.powerinv.coss4.entity.UserPlayer;

import java.util.ArrayList;
import java.util.List;

public class UserPlayerTransformUtil {
    public static boolean WITH_PID = true;
    public static boolean WITHOUT_PID = false;

    /**
     * Comp实体类转VO
     * @param userPlayer 数据库查询结果
     * @return 转换成的VO对象
     */
    public static UserPlayerVO entity2Vo(UserPlayer userPlayer) {
        if (userPlayer == null) {
            return null;
        }
        return new UserPlayerVO(userPlayer.getId(), userPlayer.getUserName(), userPlayer.getImageUrl(),
                userPlayer.getCompId(), userPlayer.getUserId(), userPlayer.getCreateTime(), userPlayer.getUpdateTime());
    }

    /**
     * Comp实体类列表转VO列表
     * @param userPlayerList 数据库查询结果
     * @return 转换成的VO对象列表
     */
    public static List<UserPlayerVO> entity2VoList(List<UserPlayer> userPlayerList) {
        List<UserPlayerVO> userPlayerVOList = new ArrayList<>();
        if (userPlayerList != null && userPlayerList.size() != 0) {
            for (UserPlayer userPlayer : userPlayerList) {
                userPlayerVOList.add(entity2Vo(userPlayer));
            }
        }
        return userPlayerVOList;
    }

    /**
     * DTO转Comp实体类
     * @param userPlayerDTO 请求参数
     * @return 转换结果
     */
    public static UserPlayer vo2Entity(UserPlayerDTO userPlayerDTO) {
        return vo2Entity(userPlayerDTO, UserPlayerTransformUtil.WITHOUT_PID);
    }

    /**
     * DTO转Comp实体类
     * @param userPlayerDTO 请求参数
     * @return 转换结果
     */
    public static UserPlayer vo2Entity(UserPlayerDTO userPlayerDTO, boolean ifPid) {
        if (userPlayerDTO == null) {
            return null;
        }
        UserPlayer userPlayer = new UserPlayerExt();
        if (ifPid) {
           userPlayer.setId(userPlayerDTO.getId());
        }
        userPlayer.setId(userPlayerDTO.getId());
        userPlayer.setUserName(userPlayerDTO.getUserName());
        userPlayer.setImageUrl(userPlayerDTO.getImageUrl());
        userPlayer.setCompId(userPlayerDTO.getCompId());
        userPlayer.setUserId(userPlayerDTO.getUserId());
        return userPlayer;
    }
}
