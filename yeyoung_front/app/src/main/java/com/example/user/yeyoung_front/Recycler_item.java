package com.example.user.yeyoung_front;

public class Recycler_item {
    int image;
    String title;
    String percent;

    int getImage(){
        return this.image;
    }
    String getTitle(){
        return this.title;
    }
    String getPercent(){return this.percent;}

    Recycler_item(int image, String title, String percent){
        this.image=image;
        this.title=title;
        this.percent=percent;
    }
}