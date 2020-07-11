package com.fubic.scrollserverweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fubic.aspect.annotation.RequestLog;
import com.fubic.dto.WeaponEmailDTO;
import com.fubic.model.Weapon;
import com.fubic.myInterface.ICalculateService;
import com.fubic.service.RMQSenderMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mxd")
public class ScrollController {
    private static final Logger log = LoggerFactory.getLogger(ScrollController.class);

    @Reference
    ICalculateService calculateService;

    @Autowired
    RMQSenderMailService rmqSenderMailService;


    @PostMapping("/calculate")
    @RequestLog(description = "计算卷轴组合")
    public String calculate(@RequestBody WeaponEmailDTO weaponEmailDTO) {
        try {
            String[] possibleScrolls = weaponEmailDTO.getWeapon().getPossibleScrolls();
            if (possibleScrolls.length == 0)
                return "未选中任何卷轴";
            if (weaponEmailDTO.getWeapon().getGrade() != 140 && weaponEmailDTO.getWeapon().getGrade() != 150 && weaponEmailDTO.getWeapon().getGrade() != 160 && weaponEmailDTO.getWeapon().getGrade() != 200)
                return "装备等级，装备等级为武器原始装备等级，扣减的等级不算";
            if (possibleScrolls.length >= 6) {
                rmqSenderMailService.sendCalculateMsg(weaponEmailDTO);
                return "全选卷轴：结果将以email形式发送给您";
            } else {
                return calculateService.getResult(weaponEmailDTO.getWeapon());
            }
        } catch (Exception e) {
            log.error("计算卷轴页面-输入有错-计算失败:{}", e.getMessage());
            return "输入有错:" + e.getMessage();
        }
    }

    public void setDefaultPage(Model model) {
        //设置是否用了X卷
        Map<String, Integer> hasXes = new HashMap<>();
        hasXes.put("是", 1);
        hasXes.put("否", 0);
        model.addAttribute("hasXes", hasXes);

        List<String> scrolls = new ArrayList<>();
        scrolls.add("惊人卷");
        scrolls.add("RED卷");
        scrolls.add("V卷");
        scrolls.add("星火卷");
        scrolls.add("痕迹");
        scrolls.add("X卷");
        model.addAttribute("scrolls", scrolls);
    }
}
