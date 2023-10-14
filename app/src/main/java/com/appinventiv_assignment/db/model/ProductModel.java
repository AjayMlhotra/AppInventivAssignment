/*
package com.appinventiv_assignment.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.appinventiv_assignment.db.constant.DBConstant.DATABASE_TABLE;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = DATABASE_TABLE.PRODUCT_DETAIL)
public class ProductModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("id")
    //@ColumnInfo(name = "server_id")
    private Integer server_id;
   // @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;
  //  @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;
   // @ColumnInfo(name = "price")
    @SerializedName("price")
    private Integer price;
   // @ColumnInfo(name = "discountPercentage")
    @SerializedName("discountPercentage")
    private Double discountPercentage;
   // @ColumnInfo(name = "rating")
    @SerializedName("rating")
    private Double rating;
   // @ColumnInfo(name = "stock")
    @SerializedName("stock")
    private Integer stock;
   // @ColumnInfo(name = "brand")
    @SerializedName("brand")
    private String brand;
  //  @ColumnInfo(name = "category")
    @SerializedName("category")
    private String category;
   // @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    private String thumbnail;
   //@ColumnInfo(name = "images")
   */
/*@SerializedName("images")
   private List<String> images;*//*


    public Integer getServer_id() {
        return server_id;
    }

    public void setServer_id(Integer server_id) {
        this.server_id = server_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

   */
/* public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }*//*

}*/
