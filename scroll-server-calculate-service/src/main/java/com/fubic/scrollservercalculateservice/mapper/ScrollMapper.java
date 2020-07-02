package com.fubic.scrollservercalculateservice.mapper;

import com.fubic.entity.WeaponScroll;
import org.springframework.stereotype.Repository;

import java.util.List;

//        mybatis流程：
//        1. 添加依赖
//        2. 创建接口类
//        3. 根据接口类的方法编写mapper.xml
//        4. application.properties添加配置（数据库连接配置，mybatis扫描配置）
//        5. 启动类里添加mapper接口扫描

@Repository
public interface ScrollMapper {
    int save(WeaponScroll scroll);

    WeaponScroll selectById(String id);

    List<WeaponScroll> selectAll();

}
