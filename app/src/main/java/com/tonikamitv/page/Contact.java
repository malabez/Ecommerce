package com.tonikamitv.page;
import com.orm.SugarRecord;


public class Contact extends SugarRecord {
    private String name;
    private String phone,image ,price;

    public Contact() {
    }

    public Contact(String name, String phone,String image, String price) {
        this.name = name;
        this.phone= phone;
        this.image= image;
        this.price= price;

    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }
    public void setimage(String image){
        this.image= image;
    }
    public  String getimage(){
        return image;
    }
    public void setprice(String price){
        this.price= price;
    }
    public  String getprice(){
        return price;
    }


    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " phone: " + phone +"image"+image+"price"+price;
    }
}