package com.fubic.scrollservercalculateservice.boot;



import com.fubic.entity.WeaponScroll;
import com.fubic.myInterface.IRedisService;
import com.fubic.scrollservercalculateservice.mapper.ScrollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ScrollMapper scrollMapper;

    @Autowired
    private IRedisService redisService;

    @Override
    public void run(String... strings) throws Exception {
        WeaponScroll scroll1 = new WeaponScroll();
        scroll1.setName("惊人卷");
        scroll1.setId("0");
        scroll1.setMain_attribute(3);
        scroll1.setVice_attribute(3);
        scroll1.setAttack(10);
        scrollMapper.save(scroll1);
        redisService.save(scroll1);

        WeaponScroll scroll2 = new WeaponScroll();
        scroll2.setName("RED卷");
        scroll2.setId("1");
        scroll2.setMain_attribute(3);
        scroll2.setVice_attribute(3);
        scroll2.setAttack(9);
        scrollMapper.save(scroll2);
        redisService.save(scroll2);

        WeaponScroll scroll3 = new WeaponScroll();
        scroll3.setName("V卷");
        scroll3.setId("2");
        scroll3.setMain_attribute(10);
        scroll3.setVice_attribute(10);
        scroll3.setAttack(12);
        scrollMapper.save(scroll3);
        redisService.save(scroll3);

        WeaponScroll scroll4 = new WeaponScroll();
        scroll4.setName("x卷9攻");
        scroll4.setId("3");
        scroll4.setMain_attribute(8);
        scroll4.setVice_attribute(8);
        scroll4.setAttack(9);//9~12
        scrollMapper.save(scroll4);
        redisService.save(scroll4);

        WeaponScroll scroll5 = new WeaponScroll();
        scroll5.setName("星火卷8攻");
        scroll5.setId("4");
        scroll5.setMain_attribute(0);
        scroll5.setVice_attribute(0);
        scroll5.setAttack(8);//8~11
        scrollMapper.save(scroll5);
        redisService.save(scroll5);

        WeaponScroll scroll6 = new WeaponScroll();
        scroll6.setName("100%痕迹");
        scroll6.setId("5");
        scroll6.setMain_attribute(0);
        scroll6.setVice_attribute(0);
        scroll6.setAttack(3);
        scrollMapper.save(scroll6);
        redisService.save(scroll6);

        WeaponScroll scroll7 = new WeaponScroll();
        scroll7.setName("70%痕迹");
        scroll7.setId("6");
        scroll7.setMain_attribute(1);
        scroll7.setVice_attribute(0);
        scroll7.setAttack(4);
        scrollMapper.save(scroll7);
        redisService.save(scroll7);

        WeaponScroll scroll8 = new WeaponScroll();
        scroll8.setName("30%痕迹");
        scroll8.setId("7");
        scroll8.setMain_attribute(2);
        scroll8.setVice_attribute(0);
        scroll8.setAttack(5);
        scrollMapper.save(scroll8);
        redisService.save(scroll8);

        WeaponScroll scroll9 = new WeaponScroll();
        scroll9.setName("15%痕迹");
        scroll9.setId("8");
        scroll9.setMain_attribute(3);
        scroll9.setVice_attribute(0);
        scroll9.setAttack(7);
        scrollMapper.save(scroll9);
        redisService.save(scroll9);

        WeaponScroll scroll10 = new WeaponScroll();
        scroll10.setName("x卷10攻");
        scroll10.setId("9");
        scroll10.setMain_attribute(8);
        scroll10.setVice_attribute(8);
        scroll10.setAttack(10);//X卷10攻
        scrollMapper.save(scroll10);
        redisService.save(scroll10);

        WeaponScroll scroll11 = new WeaponScroll();
        scroll11.setName("x卷11攻");
        scroll11.setId("10");
        scroll11.setMain_attribute(8);
        scroll11.setVice_attribute(8);
        scroll11.setAttack(11);//X卷11攻
        scrollMapper.save(scroll11);
        redisService.save(scroll11);

        WeaponScroll scroll12 = new WeaponScroll();
        scroll12.setName("x卷12攻");
        scroll12.setId("11");
        scroll12.setMain_attribute(8);
        scroll12.setVice_attribute(8);
        scroll12.setAttack(12);//X卷12攻
        scrollMapper.save(scroll12);
        redisService.save(scroll12);

        WeaponScroll scroll13 = new WeaponScroll();
        scroll13.setName("星火卷9攻");
        scroll13.setId("12");
        scroll13.setMain_attribute(0);
        scroll13.setVice_attribute(0);
        scroll13.setAttack(9);//8~11
        scrollMapper.save(scroll13);
        redisService.save(scroll13);

        WeaponScroll scroll14 = new WeaponScroll();
        scroll14.setName("星火卷10攻");
        scroll14.setId("13");
        scroll14.setMain_attribute(0);
        scroll14.setVice_attribute(0);
        scroll14.setAttack(10);//8~11
        scrollMapper.save(scroll14);
        redisService.save(scroll14);

        WeaponScroll scroll15 = new WeaponScroll();
        scroll15.setName("星火卷11攻");
        scroll15.setId("14");
        scroll15.setMain_attribute(0);
        scroll15.setVice_attribute(0);
        scroll15.setAttack(11);//8~11
        scrollMapper.save(scroll15);
        redisService.save(scroll15);

    }
}
