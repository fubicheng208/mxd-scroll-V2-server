package com.fubic.myInterface;

public interface ISendMailService {
    void sendEmail();

    /**
    * @Description: 使用邮件发送计算结果
    * @Param: 
    * @return: 
    * @Author: fubicheng
    * @Date: 2020/7/26
    */
    void sendCalculateWeaponEmail(String email, String content);
}

