package com.fubic.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class WeaponScroll implements Serializable {

    @Id
    private String id;
//    private int id;

    private String name;
    private int main_attribute;
    private int vice_attribute;
    private int attack;

    public WeaponScroll() {
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMain_attribute() {
        return main_attribute;
    }

    public void setMain_attribute(int main_attribute) {
        this.main_attribute = main_attribute;
    }

    public int getVice_attribute() {
        return vice_attribute;
    }

    public void setVice_attribute(int vice_attribute) {
        this.vice_attribute = vice_attribute;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
