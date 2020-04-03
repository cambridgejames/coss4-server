package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.competition.service.BaseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/base")
public class BaseInformationController {

    private BaseInformationService baseInformationService;

    @Autowired
    public void setBaseInformationService(BaseInformationService baseInformationService) {
        this.baseInformationService = baseInformationService;
    }

    @ResponseBody
    @RequestMapping("/createCompetition")
    public Map<String, Object> createCompetition() {
        return baseInformationService.createCompetition();
    }
}
