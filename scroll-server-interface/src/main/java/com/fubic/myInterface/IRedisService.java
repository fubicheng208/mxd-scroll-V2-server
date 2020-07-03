package com.fubic.myInterface;

import com.fubic.entity.WeaponScroll;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRedisService {
    boolean save(WeaponScroll scroll);

    WeaponScroll selectById(String id);

}
