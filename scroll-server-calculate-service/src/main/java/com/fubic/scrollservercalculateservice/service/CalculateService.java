package com.fubic.scrollservercalculateservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fubic.aspect.annotation.MethodLog;
import com.fubic.entity.WeaponScroll;
import com.fubic.model.IdAttr;
import com.fubic.model.Weapon;
import com.fubic.myInterface.ICalculateService;
import com.fubic.myInterface.IRedisService;
import com.fubic.scrollservercalculateservice.mapper.ScrollMapper;
import com.fubic.scrollservercalculateservice.utils.BFSCombination;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*dubbo服务注册流程
1. 引入dubbo依赖；
2. 配置文件注册；
3. 实现接口类；
4. provider实现接口类并加注解表示这是一个dubbo服务对外提供；
5. 启动类加@EnableDubbo。*/

@Service
@Component
public class CalculateService implements ICalculateService {
    private static final Logger log = LoggerFactory.getLogger(CalculateService.class);
//    @Autowired
//    private WeaponScrollRepository weaponScrollRepository;

    @Autowired
    private ScrollMapper scrollMapper;

    @Autowired
    private IRedisService redisService;


    @Override
    public List<WeaponScroll> getAllScroll() {
//        return weaponScrollRepository.findAll();
        return scrollMapper.selectAll();
    }


    @Override
    @MethodLog
    public String getResult(Weapon weapon) throws Exception {

//        List<WeaponScroll> scrolls = getAllScroll();
        List<WeaponScroll> scrolls = getSelectedScroll(weapon.getPossibleScrolls());
        //基础攻击+卷+星
        int scrollStarTotalAttack = weapon.getBase_attack() + weapon.getBuf_attack();
        //基础攻击+卷
        int scrollTotalAttack = scrollStarTotalAttack;
        int starNum = weapon.getStarNum();
        int grade = weapon.getGrade();
        int scrollNum = weapon.getScrollNum();
        //基础主属性增加:卷+星
        int scrollStarTotalMainAttr = weapon.getMain_attribute();
        int scrollTotalMainAttr = scrollStarTotalMainAttr;

        for (int i = starNum; i > 0; i--) {
            scrollTotalAttack = calStarAttackOnce(scrollTotalAttack, i, grade);
            scrollTotalMainAttr = calStarMainAttrOnce(scrollTotalMainAttr, i, grade);
        }
        //卷轴所加攻击
        int scrollAttack = scrollTotalAttack - weapon.getBase_attack();
        //卷轴所加主属性
        int scrollMainAttr = scrollTotalMainAttr;
        //卷轴所加无关属性
        int scrollViceAttr = weapon.getVice_attribute();

        List<IdAttr> lsAttack = new ArrayList<>();
//        List<IdAttr> lsMainAttr = new ArrayList<>();
//        List<IdAttr> lsViceAttr = new ArrayList<>();
        HashMap<String, Integer> mMainAttr = new HashMap<>();
        HashMap<String, Integer> mViceAttr = new HashMap<>();


        for (WeaponScroll scroll : scrolls) {
            lsAttack.add(new IdAttr(scroll.getId(), scroll.getAttack()));
            mMainAttr.put(scroll.getId(), scroll.getMain_attribute());
            mViceAttr.put(scroll.getId(), scroll.getVice_attribute());
//            lsMainAttr.add(new IdAttr(scroll.getId(), scroll.getMain_attribute()));
//            lsViceAttr.add(new IdAttr(scroll.getId(), scroll.getVice_attribute()));
        }

        List<List<String>> resAttack = BFSCombination.getCombination(lsAttack, scrollAttack, scrollNum);
        if (resAttack.size() == 0)
            throw new Exception("计算出错,请检查您的输入");
        resAttack = filterByMainAndViceAttribute(resAttack, mMainAttr, mViceAttr, scrollMainAttr, scrollViceAttr);
        if (resAttack.size() == 0)
            throw new Exception("计算出错，请检查您的输入");

//        List<List<String>> resMainAttr = BFSCombination.getCombination(lsMainAttr, scrollMainAttr, scrollNum);
//        if (resMainAttr.size()==0)
//            throw new Exception("计算出错");
//        List<List<String>> resViceAttr = BFSCombination.getCombination(lsViceAttr, scrollViceAttr, scrollNum);
//        if (resViceAttr.size()==0)
//            throw new Exception("计算出错");

//        resAttack.retainAll(resMainAttr);
//        resAttack.retainAll(resViceAttr);

        return getResultFromCollection(resAttack);

    }


    @Override
    public List<WeaponScroll> getSelectedScroll(String[] selected) {
        List<WeaponScroll> res = new ArrayList<>();
        for (String name : selected) {
            switch (name) {
                case "惊人卷":
//                    res.add(scrollMapper.selectById("0"));
                    res.add(redisService.selectById("0"));
                    break;
                case "RED卷":
//                    res.add(scrollMapper.selectById("1"));
                    res.add(redisService.selectById("1"));
                    break;
                case "V卷":
//                    res.add(scrollMapper.selectById("2"));
                    res.add(redisService.selectById("2"));
                    break;
                case "X卷":
//                    res.add(scrollMapper.selectById("3"));
                    res.add(redisService.selectById("3"));
//                    res.add(scrollMapper.selectById("9"));
                    res.add(redisService.selectById("9"));
//                    res.add(scrollMapper.selectById("10"));
                    res.add(redisService.selectById("10"));
//                    res.add(scrollMapper.selectById("11"));
                    res.add(redisService.selectById("11"));
                    break;
                case "星火卷":
                    /*res.add(scrollMapper.selectById("4"));
                    res.add(scrollMapper.selectById("12"));
                    res.add(scrollMapper.selectById("13"));
                    res.add(scrollMapper.selectById("14"));*/
                    res.add(redisService.selectById("4"));
                    res.add(redisService.selectById("12"));
                    res.add(redisService.selectById("13"));
                    res.add(redisService.selectById("14"));
                    break;
                case "痕迹":
                    /*res.add(scrollMapper.selectById("5"));
                    res.add(scrollMapper.selectById("6"));
                    res.add(scrollMapper.selectById("7"));
                    res.add(scrollMapper.selectById("8"));*/
                    res.add(redisService.selectById("5"));
                    res.add(redisService.selectById("6"));
                    res.add(redisService.selectById("7"));
                    res.add(redisService.selectById("8"));
                    break;
                default:
                    break;
            }
        }
        return res;
    }

    @Override
    public List<List<String>> filterByMainAndViceAttribute(List<List<String>> attackCollection, HashMap<String, Integer> mMainAttr, HashMap<String, Integer> mViceAttr, int scrollMainAttr, int scrollViceAttr) {
        List<List<String>> res = new ArrayList<>();
        for (List<String> scrolls : attackCollection) {
            int totalMainAttribute = 0;
            int totalViceAttribute = 0;
            for (String id : scrolls) {
                totalMainAttribute += mMainAttr.get(id);
                totalViceAttribute += mViceAttr.get(id);
            }
            if (totalMainAttribute == scrollMainAttr && totalViceAttribute == scrollViceAttr) {
                res.add(scrolls);
            }
        }
        return res;
    }


    private String getResultFromCollection(List<List<String>> resList) {
        String res = "";
        for (List<String> ls : resList) {
            Map<String, Integer> map = new HashMap<>();
            for (String id : ls) {
//                WeaponScroll ws = (WeaponScroll) weaponScrollRepository.getOne(id);
//                WeaponScroll ws = (WeaponScroll) scrollMapper.selectById(id);
                WeaponScroll ws = redisService.selectById(id);
                String name = ws.getName();
                if (map.containsKey(name))
                    map.put(name, map.get(name) + 1);
                else
                    map.put(name, 1);
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                res += entry.getKey() + entry.getValue() + "张";
            }
            res += "  或者  ";
        }
        return res.substring(0, res.length() - 4);
    }

    private int calStarMainAttrOnce(int y, int star, int grade) throws Exception {
        if (star <= 15) {
            if (star <= 5)
                return y - 2;
            else
                return y - 3;
        } else if (star <= 22) {
            if (grade == 160)
                return y - 13;
            else if (grade == 150)
                return y - 11;
            else if (grade == 140)
                return y - 9;
            else
                throw new Exception("装备等级为200级且星数大于15，本人没找到相关数据，无法计算，提供数据可联系企鹅：651011591");
        } else {
            throw new Exception("武器星数大于22，本人没找到相关数据，无法计算，提供数据可联系企鹅：651011591");
        }
    }

    private int calStarAttackOnce(int y, int star, int grade) throws Exception {
        if (star <= 15) {
            int x = (y - 1) * 50 / 51 + 1;
            int a = x - 1;
            int b = x + 1;
            if (a + a / 50 + 1 == y)
                return a;
            if (x + x / 50 + 1 == y)
                return x;
            if (b + b / 50 + 1 == y)
                return b;
            return x;
        } else {
            //16星以上计算根据武器等级不同有变化
            if (grade == 160) {
                switch (star) {
                    case 16:
                        return y - 9;
                    case 17:
                        return y - 10;
                    case 18:
                        return y - 10;
                    case 19:
                        return y - 11;
                    case 20:
                        return y - 12;
                    case 21:
                        return y - 13;
                    case 22:
                        return y - 14;
                    default:
                        throw new Exception("武器星数大于22，暂时无法计算");
                }
            } else if (grade == 150) {
                switch (star) {
                    case 16:
                        return y - 8;
                    case 17:
                        return y - 9;
                    case 18:
                        return y - 9;
                    case 19:
                        return y - 10;
                    case 20:
                        return y - 11;
                    case 21:
                        return y - 12;
                    case 22:
                        return y - 13;
                    default:
                        throw new Exception("武器星数大于22，暂时无法计算");
                }
            } else if (grade == 140) {
                switch (star) {
                    case 16:
                        return y - 7;
                    case 17:
                        return y - 8;
                    case 18:
                        return y - 8;
                    case 19:
                        return y - 9;
                    case 20:
                        return y - 10;
                    case 21:
                        return y - 11;
                    case 22:
                        return y - 12;
                    default:
                        throw new Exception("武器星数大于22，暂时无法计算");
                }
            } else {
                throw new Exception("200级武器星数大于15，本人没找到相关数据，无法计算，提供数据可联系企鹅：651011591");
            }
        }
    }

}
