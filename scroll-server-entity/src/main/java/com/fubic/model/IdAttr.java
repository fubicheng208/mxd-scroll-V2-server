package com.fubic.model;

public class IdAttr {
    //    private int id;
    private String id;
    private int attr;

    public IdAttr() {
    }

    public IdAttr(String id, int attr) {
        this.id = id;
        this.attr = attr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //    public IdAttr(int id, int attr) {
//        this.id = id;
//        this.attr = attr;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public int getAttr() {
        return attr;
    }

    public void setAttr(int attr) {
        this.attr = attr;
    }
}
