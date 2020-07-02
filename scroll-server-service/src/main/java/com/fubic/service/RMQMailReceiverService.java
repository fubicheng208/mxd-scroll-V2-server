package com.fubic.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fubic.dto.WeaponEmailDTO;
import com.fubic.myInterface.ICalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*调用dubbo服务流程：
1. 引入依赖；
2. 配置文件注册消费者；
3. 启动类加@EnableDubbo;
4. 使用加@Reference。*/
@Service
public class RMQMailReceiverService {
    public static final Logger log = LoggerFactory.getLogger(RMQMailReceiverService.class);

    @Reference
    ICalculateService calculateService;

    @Autowired
    SendMailService sendMailService;

    /**
     * @Description: 根据RabbitMQ收到的消息，执行武器卷轴计算, 并发送结果给用户邮箱
     * @Param:
     * @return:
     * @Author: fubicheng
     * @Date: 2020/5/21
     */
    @RabbitListener(queues = {"${mq.mxd.success.email.queue}"}, containerFactory = "singleListenerContainer")
    public void consumeWeaponEmailMsg(WeaponEmailDTO weaponEmailDTO) {
        try {
            log.info("计算卷轴任务-接收到武器数据为：{}", weaponEmailDTO);
            String result = calculateService.getResult(weaponEmailDTO.getWeapon());
            sendMailService.sendCalculateWeaponEmail(weaponEmailDTO.getEmail(), result);
            log.info("计算卷轴任务-发送计算结果邮件-成功");
        } catch (Exception e) {
            log.error("计算卷轴任务-失败：", e.fillInStackTrace());
        }
    }
}