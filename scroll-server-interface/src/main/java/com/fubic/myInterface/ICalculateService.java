package com.fubic.myInterface;

import com.fubic.entity.WeaponScroll;
import com.fubic.model.Weapon;

import java.util.HashMap;
import java.util.List;

public interface ICalculateService {
    List<WeaponScroll> getAllScroll();

    String getResult(Weapon weapon) throws Exception;

    List<WeaponScroll> getSelectedScroll(String[] selected);

    List<List<String>> filterByMainAndViceAttribute(List<List<String>> attackCollection, HashMap<String, Integer> mMainAttr, HashMap<String, Integer> mViceAttr, int scrollMainAttr, int scrollViceAttr);
}