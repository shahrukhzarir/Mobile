package com.shahrukhzarir.assignmenttwo;

/**
 * Created by shahrukhzarir on 2017-11-15.
 */

public class Product {
    private String name ="";
    private String description = "";

    private double price = 0.0;

    public Product(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){

        return description;
    }

    public double getPrice(){
        return price;
    }

    public String toString(){
        return name + " , " + description + ", " + price;
    }
}