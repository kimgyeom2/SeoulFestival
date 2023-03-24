package com.gy25m.seoulfestival;

public class FestivalItem {
    String titleImg;
    String title;
    String codeName;
    String place;
    String date;

    public FestivalItem() {
    }

    public FestivalItem(String titleImg,String title, String codeName, String place, String date) {
        this.titleImg=titleImg;
        this.title = title;
        this.codeName = codeName;
        this.place = place;
        this.date = date;
    }
}
