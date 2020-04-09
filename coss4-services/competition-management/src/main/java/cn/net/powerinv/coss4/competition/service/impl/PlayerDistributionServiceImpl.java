package cn.net.powerinv.coss4.competition.service.impl;

import cn.net.powerinv.coss4.basic.util.CommonResultUtil;
import cn.net.powerinv.coss4.basic.util.MessageCode;
import cn.net.powerinv.coss4.competition.mapper.LinkGroupPlayerMapper;
import cn.net.powerinv.coss4.competition.service.PlayerDistributionService;
import cn.net.powerinv.coss4.competition.vo.LinkGroupPlayerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class PlayerDistributionServiceImpl implements PlayerDistributionService {

    private LinkGroupPlayerMapper linkGroupPlayerMapper;

    @Autowired
    public void setLinkGroupPlayerMapper(LinkGroupPlayerMapper linkGroupPlayerMapper) {
        this.linkGroupPlayerMapper = linkGroupPlayerMapper;
    }

    /**
     * 为选手添加分组
     *
     * @param linkGroupPlayerDTO 分组信息
     * @return 添加结果
     */
    @Override
    public Map<String, Object> addGroupForPlayer(LinkGroupPlayerDTO linkGroupPlayerDTO) {
        if (linkGroupPlayerMapper.insertWithCheck(linkGroupPlayerDTO) != 1) {
            return CommonResultUtil.returnFalse(MessageCode.OTHER_ERROR);
        }
        linkGroupPlayerDTO.setUserId(null);
        return CommonResultUtil.returnTrue(linkGroupPlayerDTO);
    }
}
