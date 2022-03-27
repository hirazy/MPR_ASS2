package com.example.mpr_ass2.model;

public class ProductDatabase {

    public int id;
    public String thumbnail;
    public String name;
    public long unitPrice;
    public int quantity;

    public ProductDatabase(int id, String thumbnail, String name, long unitPrice, int quantity) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
