package com.paperless.app.model;

import java.io.Serializable;

public class ItemData implements Serializable {
    private String name;

    private String desc;

    private int price;

    public ItemData() {

    }

    public ItemData(String name, String desc, int price) {
        this.name = name;
        this.price = price;
        this.desc = desc;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
