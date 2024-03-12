package com.superrecipesapp.app.Models;

import android.widget.ImageView;

public class OrdersListModel {
    String title, description;
    int price;
    int thumbnail;
    public OrdersListModel( String title, String description, int price, int thumbnail)
    {
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setThumbnail(int img) {
        this.thumbnail = img;
    }

    public int getThumbnail() {
        return thumbnail;
    }

}
