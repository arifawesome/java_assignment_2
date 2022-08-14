package com.example.java_final_1.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies_tvs")
public class MoviesAndTvs {

    @Id
    private String id;
    private String title;
    private double price;
    private String synopsis;
    private String type;
    private String smallPosterUrl;
    private String bigPosterUrl;
    private double rentPrice;
    private double buyPrice;
    private boolean featured;

    public MoviesAndTvs() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSmallPosterUrl() {
        return smallPosterUrl;
    }

    public void setSmallPosterUrl(String smallPosterUrl) {
        this.smallPosterUrl = smallPosterUrl;
    }

    public String getBigPosterUrl() {
        return bigPosterUrl;
    }

    public void setBigPosterUrl(String bigPosterUrl) {
        this.bigPosterUrl = bigPosterUrl;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public MoviesAndTvs(String title, double price, String synopsis, String type, String smallPosterUrl, String bigPosterUrl, double rentPrice, double buyPrice, boolean featured){
        this.title=title;
        this.synopsis=synopsis;
        this.price=price;
        this.smallPosterUrl=smallPosterUrl;
        this.bigPosterUrl=bigPosterUrl;
        this.rentPrice=rentPrice;
        this.buyPrice=buyPrice;
        this.type=type;
        this.featured=featured;
    }

    @Override
    public String toString(){
        return String.format("MoviesAndTvs[_id='%s', title='%s', synopsis='%s', price='%f', smallPosterUrl='%s',bigPosterUrl='%s',rentPrice='%f'," +
                "buyPrice='%f', type='%s', featured='%b']", id, title, synopsis, price, smallPosterUrl, bigPosterUrl, rentPrice, buyPrice, type, featured);
    }
}
