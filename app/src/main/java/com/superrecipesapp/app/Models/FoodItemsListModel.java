package com.superrecipesapp.app.Models;

public class FoodItemsListModel {
    int orderNo, price, thumbnail;
    String title;
    public FoodItemsListModel(String title, int orderNo, int price, int thumbnail)
    {
        this.title = title;
        this.orderNo = orderNo;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPrice() {
        return price;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public String getTitle() {
        return title;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}