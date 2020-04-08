package cn.net.powerinv.coss4.competition.mapper;

import cn.net.powerinv.coss4.competition.entity.UserPlayerExt;
import cn.net.powerinv.coss4.competition.vo.UserPlayerDTO;
import cn.net.powerinv.coss4.entity.UserPlayer;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Component;

@Component
public interface UserPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPlayer record);

    int insertSelective(UserPlayer record);

    UserPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPlayer record);

    int updateByPrimaryKey(UserPlayer record);

    int insertWithCheck(UserPlayerExt userPlayerExt);

    int updateWithCheck(UserPlayerExt userPlayerExt);

    UserPlayer selectWithCheck(UserPlayerExt userPlayerExt);

    Page<UserPlayer> queryPlayerList(UserPlayerDTO userPlayerDTO);

    int deleteWithCheck(UserPlayerExt userPlayerExt);
}