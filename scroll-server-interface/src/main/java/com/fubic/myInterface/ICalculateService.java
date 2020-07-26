package com.fubic.myInterface;

import com.fubic.entity.WeaponScroll;
import com.fubic.model.CalculateResult;
import com.fubic.model.Weapon;

import java.util.HashMap;
import java.util.List;

public interface ICalculateService {
    /**
    * @Description: 返回所有的武器卷轴信息
    * @Param:
    * @return:
    * @Author: fubicheng
    * @Date: 2020/7/26
    */
    List<WeaponScroll> getAllScroll();

    /**
    * @Description: 根据武器属性计算可能的卷轴组合，返回字符串
    * @Param:
    * @return:
    * @Author: fubicheng
    * @Date: 2020/7/26
    */
    String getResult(Weapon weapon) throws Exception;

    /**
    * @Description: 根据武器属性计算可能的卷轴组合，返回保存计算结果的列表
    * @Param:
    * @return:
    * @Author: fubicheng
    * @Date: 2020/7/26
    */
    List<CalculateResult> getResultList(Weapon weapon) throws Exception;

    /**
    * @Description: 选中的卷轴有哪些
    * @Param:
    * @return:
    * @Author: fubicheng
    * @Date: 2020/7/26
    */
    List<WeaponScroll> getSelectedScroll(String[] selected);

    /**
    * @Description: 先根据主、副属性过滤，减少计算量
    * @Param:
    * @return:
    * @Author: fubicheng
    * @Date: 2020/7/26
    */
    List<List<String>> filterByMainAndViceAttribute(List<List<String>> attackCollection, HashMap<String, Integer> mMainAttr, HashMap<String, Integer> mViceAttr, int scrollMainAttr, int scrollViceAttr);
}