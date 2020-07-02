package com.fubic.scrollserverweb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fubic.dto.WeaponEmailDTO;
import com.fubic.model.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScrollServerWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getTestJson(){
        Weapon weapon = new Weapon(14,12,169,252,157,120,160);
        weapon.setPossibleScrolls(new String[]{"惊人卷","RED卷","V卷"});
        WeaponEmailDTO weaponEmailDTO = new WeaponEmailDTO(weapon, "fubicheng208@126.com");
        System.out.println(JSON.toJSONString(weaponEmailDTO));
    }

}
