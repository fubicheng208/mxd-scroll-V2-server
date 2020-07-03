package com.fubic.scrollservercalculateservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fubic.aspect.enums.MyConstants;
import com.fubic.entity.WeaponScroll;
import com.fubic.model.Weapon;
import com.fubic.myInterface.IRedisService;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class RedisService implements IRedisService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean save(WeaponScroll scroll) {
        try{
            redisTemplate.opsForValue().set(MyConstants.RedisKey.SCROLLS.getMessage() + scroll.getId(), JSON.toJSONString(scroll));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public WeaponScroll selectById(String id) {
        return id == null ? null : JSON.parseObject((String)redisTemplate.opsForValue().get(MyConstants.RedisKey.SCROLLS.getMessage() + id), new TypeReference<WeaponScroll>(){});
    }

}
