package com.fubic.service;

import com.fubic.myInterface.ISendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class SendMailService implements ISendMailService {

    private static final Logger log = LoggerFactory.getLogger(SendMailService.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    /**
     * @Description: 测试发送email功能
     * @Param:
     * @return:
     * @Author: fubicheng
     * @Date: 2020/5/21
     */
    @Override
    public void sendEmail() {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("email测试");
        smm.setText("正文");
        smm.setTo("fubicheng208@126.com");
        smm.setFrom("651011591@qq.com");
        javaMailSender.send(smm);
    }

    /**
     * @Description: 发送武器卷轴计算结果
     * @Param:
     * @return:
     * @Author: fubicheng
     * @Date: 2020/5/21
     */
    @Override
    public void sendCalculateWeaponEmail(String email, String content) {
        try {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setSubject("冒险岛卷轴计算结果");
            smm.setText(content);
            smm.setTo(email);
            smm.setFrom("651011591@qq.com");
            javaMailSender.send(smm);
            log.info("发送卷轴计算结果-发送成功");
        } catch (Exception e) {
            log.error("发送卷轴计算结果-发生异常：", e.fillInStackTrace());
        }
    }

}