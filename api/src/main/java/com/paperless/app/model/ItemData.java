package com.paperless.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class ItemData implements Serializable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("price")
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
