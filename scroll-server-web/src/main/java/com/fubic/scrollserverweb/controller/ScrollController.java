package com.fubic.scrollserverweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fubic.aspect.annotation.RequestLog;
import com.fubic.aspect.enums.MyConstants;
import com.fubic.dto.ResultDTO;
import com.fubic.dto.WeaponEmailDTO;
import com.fubic.model.CalculateResult;
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


    @PostMapping("/getWeaponResult")
    @RequestLog(description = "计算卷轴组合")
    public ResultDTO<String> getWeaponResult(@RequestBody WeaponEmailDTO weaponEmailDTO) {
        try {
            String[] possibleScrolls = weaponEmailDTO.getWeapon().getPossibleScrolls();
            if (possibleScrolls.length >= 6) {
                rmqSenderMailService.sendCalculateMsg(weaponEmailDTO);
                return new ResultDTO<>(MyConstants.CalculateResultCode.SUCCESS.getCode(), MyConstants.CalculateResultCode.EMAIL.getMessage(),"全选卷轴：结果将以email形式发送给您");
            } else {
                return new ResultDTO<>(MyConstants.CalculateResultCode.SUCCESS.getCode(), MyConstants.CalculateResultCode.SUCCESS.getMessage(), calculateService.getResult(weaponEmailDTO.getWeapon()));
            }
        } catch (Exception e) {
            log.error("计算卷轴页面-输入有错-计算失败:{}", e.getMessage());
            return new ResultDTO<>(MyConstants.CalculateResultCode.FAIL.getCode(), MyConstants.CalculateResultCode.FAIL.getMessage(), null);
        }
    }

    @PostMapping("/getWeaponResultList")
    @RequestLog(description = "计算卷轴组合")
    public ResultDTO<List<CalculateResult>> getWeaponResultList(@RequestBody WeaponEmailDTO weaponEmailDTO) {
        try {
            String[] possibleScrolls = weaponEmailDTO.getWeapon().getPossibleScrolls();
            if (possibleScrolls.length >= 6) {
                rmqSenderMailService.sendCalculateMsg(weaponEmailDTO);
                return new ResultDTO<>(MyConstants.CalculateResultCode.EMAIL.getCode(), MyConstants.CalculateResultCode.EMAIL.getMessage(), null);
            } else {
                return new ResultDTO<>(MyConstants.CalculateResultCode.SUCCESS.getCode(), MyConstants.CalculateResultCode.SUCCESS.getMessage(), calculateService.getResultList(weaponEmailDTO.getWeapon()));
            }
        } catch (Exception e) {
            log.error("计算卷轴页面-输入有错-计算失败:{}", e.getMessage());
            return new ResultDTO<>(MyConstants.CalculateResultCode.FAIL.getCode(), MyConstants.CalculateResultCode.FAIL.getMessage(), null);
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
