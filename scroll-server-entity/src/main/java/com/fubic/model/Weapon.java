package com.fubic.model;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Arrays;

public class Weapon implements Serializable {

    private static final long serialVersionUID = -1571272574268027060L;
    @NotNull
    @Min(0)
    @Max(22)
    private int starNum;

    @NotNull
    @Min(0)
    @Max(20)
    private int scrollNum;

    @NotNull
    @Min(0)
    @Max(700)
    private int base_attack;//武器基础攻击

    @NotNull
    @Min(0)
    @Max(1000)
    private int buf_attack;//卷轴和星星所加的攻击/魔功数

    @NotNull
    @Min(0)
    @Max(300)
    private int main_attribute;

    @NotNull
    @Min(0)
    @Max(300)
    private int vice_attribute;

    @NotNull
    @Min(0)
    @Max(300)
    private int grade;

    @NotNull
    private int hasX;


    @NotNull
    private String[] possibleScrolls;


    public Weapon() {
    }

    public Weapon(int starNum, int scrollNum, int base_attack, int buf_attack, int main_attribute, int vice_attribute, int grade) {
        this.starNum = starNum;
        this.scrollNum = scrollNum;
        this.base_attack = base_attack;
        this.buf_attack = buf_attack;
        this.main_attribute = main_attribute;
        this.vice_attribute = vice_attribute;
        this.grade = grade;
    }

    public Weapon(int starNum, int scrollNum, int base_attack, int buf_attack, int main_attribute, int vice_attribute, int grade, int hasX) {
        this.starNum = starNum;
        this.scrollNum = scrollNum;
        this.base_attack = base_attack;
        this.buf_attack = buf_attack;
        this.main_attribute = main_attribute;
        this.vice_attribute = vice_attribute;
        this.grade = grade;
        this.hasX = 0;
    }

//    public Weapon(@NotNull @Min(0) @Max(22) int starNum, @NotNull @Min(0) @Max(20) int scrollNum, @NotNull @Min(0) @Max(700) int base_attack, @NotNull @Min(0) @Max(1000) int buf_attack, @NotNull @Min(0) @Max(300) int main_attribute, @NotNull @Min(0) @Max(300) int vice_attribute, @NotNull @Min(0) @Max(300) int grade, @NotNull int hasX, @Email int email, @NotNull String[] possibleScrolls) {
//        this.starNum = starNum;
//        this.scrollNum = scrollNum;
//        this.base_attack = base_attack;
//        this.buf_attack = buf_attack;
//        this.main_attribute = main_attribute;
//        this.vice_attribute = vice_attribute;
//        this.grade = grade;
//        this.hasX = hasX;
//        this.email = email;
//        this.possibleScrolls = possibleScrolls;
//    }

    public String[] getPossibleScrolls() {
        return possibleScrolls;
    }

    public void setPossibleScrolls(String[] possibleScrolls) {
        this.possibleScrolls = possibleScrolls;
    }

    public int getHasX() {
        return hasX;
    }

    public void setHasX(int hasX) {
        this.hasX = hasX;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public int getScrollNum() {
        return scrollNum;
    }

    public void setScrollNum(int scrollNum) {
        this.scrollNum = scrollNum;
    }


    public int getBase_attack() {
        return base_attack;
    }

    public void setBase_attack(int base_attack) {
        this.base_attack = base_attack;
    }

    public int getBuf_attack() {
        return buf_attack;
    }

    public void setBuf_attack(int buf_attack) {
        this.buf_attack = buf_attack;
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

    @Override
    public String toString() {
        return "Weapon{" +
                "starNum=" + starNum +
                ", scrollNum=" + scrollNum +
                ", base_attack=" + base_attack +
                ", buf_attack=" + buf_attack +
                ", main_attribute=" + main_attribute +
                ", vice_attribute=" + vice_attribute +
                ", grade=" + grade +
                ", hasX=" + hasX +
                ", possibleScrolls=" + Arrays.toString(possibleScrolls) +
                '}';
    }
}
