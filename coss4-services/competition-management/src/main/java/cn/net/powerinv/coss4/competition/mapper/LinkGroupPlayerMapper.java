package cn.net.powerinv.coss4.competition.mapper;

import cn.net.powerinv.coss4.competition.vo.LinkGroupPlayerDTO;
import cn.net.powerinv.coss4.entity.LinkGroupPlayer;
import org.springframework.stereotype.Component;

@Component
public interface LinkGroupPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LinkGroupPlayer record);

    int insertSelective(LinkGroupPlayer record);

    LinkGroupPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LinkGroupPlayer record);

    int updateByPrimaryKey(LinkGroupPlayer record);

    int deleteByGroupId(LinkGroupPlayer record);

    int deleteByPlayerId(LinkGroupPlayer record);

    int insertWithCheck(LinkGroupPlayerDTO linkGroupPlayerDTO);

    int removeGroupWithCheck(LinkGroupPlayerDTO linkGroupPlayerDTO);

    int updateWorkWithCheck(LinkGroupPlayerDTO linkGroupPlayerDTO);
}