package com.example.hasanzian.e_commerce;

public class DataModels {
    private String mTitle;
    private String mPrice;
    private int mImage;

    public DataModels(String mTitle, String mPrice, int mImage) {
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mImage = mImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPrice() {
        return mPrice;
    }

    public int getImage() {
        return mImage;
    }
}
