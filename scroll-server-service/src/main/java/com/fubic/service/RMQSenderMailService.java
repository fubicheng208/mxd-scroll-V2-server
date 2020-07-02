package com.fubic.service;

import com.fubic.dto.WeaponEmailDTO;
import com.fubic.model.Weapon;
import com.fubic.myInterface.ICalculateService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
public class RMQSenderMailService {
    public static final Logger log = LoggerFactory.getLogger(RMQSenderMailService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    public void sendCalculateMsg(Weapon weapon, String email) {
        WeaponEmailDTO weaponEmailDTO = new WeaponEmailDTO(weapon, email);
        log.info("准备发送计算命令给rabbitmq");
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("mq.mxd.success.email.exchange"));
            rabbitTemplate.setRoutingKey(env.getProperty("mq.mxd.success.email.routing.key"));
            rabbitTemplate.convertAndSend(weaponEmailDTO, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, WeaponEmailDTO.class);
                    return message;
                }
            });
        } catch (Exception e) {
            log.error("异步发送卷轴计算任务失败");
        }
    }

    public void sendCalculateMsg(WeaponEmailDTO weaponEmailDTO) {
        log.info("准备发送计算命令给rabbitmq");
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("mq.mxd.success.email.exchange"));
            rabbitTemplate.setRoutingKey(env.getProperty("mq.mxd.success.email.routing.key"));
            rabbitTemplate.convertAndSend(weaponEmailDTO, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, WeaponEmailDTO.class);
                    return message;
                }
            });
        } catch (Exception e) {
            log.error("异步发送卷轴计算任务失败");
        }
    }
}
