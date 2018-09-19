package com.example.hasanzian.e_commerce.model;

public class DataModels {
    private String title;
    private String price;
    private int imageUrl;
    private String downloadUrl;
    private String id;

    //fire base
    public DataModels() {
    }

    public DataModels(String title, String price, String downloadUrl) {
        this.title = title;
        this.price = price;
        this.downloadUrl = downloadUrl;
    }

    public DataModels(String title, String price, int imageUrl) {
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
