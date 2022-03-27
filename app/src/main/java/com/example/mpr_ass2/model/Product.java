package com.example.mpr_ass2.model;

public class Product {

    public int id;
    public String thumbnail;
    public String name;
    public long unitPrice;

    public Product(int id, String thumbnail, String name, long unitPrice) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.name = name;
        this.unitPrice = unitPrice;
    }
}
