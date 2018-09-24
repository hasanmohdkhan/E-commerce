package com.hasanzian.farmer.ecom.demo.model;

public class CartModel {

    private String title;
    private String price;
    private int imageUrl;
    private String downloadUrl;
    private String id;
    private String orderedTotal;
    private String orderedTitle;
    private String orderedQuantity;

    public String getOrderedTotal() {
        return orderedTotal;
    }

    public String getOrderedTitle() {
        return orderedTitle;
    }

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

    //ordered model
    public CartModel(String orderedTitle, String orderedTotal, String orderedQuantity,String downloadUrl) {
        this.downloadUrl = downloadUrl;
        this.orderedTotal = orderedTotal;
        this.orderedTitle = orderedTitle;
        this.orderedQuantity = orderedQuantity;
    }

    //Cart model
    public CartModel(String title, String price, String downloadUrl) {
        this.title = title;
        this.price = price;
        this.downloadUrl = downloadUrl;
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
