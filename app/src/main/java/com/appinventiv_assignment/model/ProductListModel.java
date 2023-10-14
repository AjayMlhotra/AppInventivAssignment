package com.appinventiv_assignment.model;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.appinventiv_assignment.R;
import com.appinventiv_assignment.db.constant.DBConstant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProductListModel {
    @SerializedName("total")
    private Integer total;
    @SerializedName("skip")
    private Integer skip;
    @SerializedName("limit")
    private Integer limit;
    @SerializedName("products")
    private List<ProductsDTO> products;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<ProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDTO> products) {
        this.products = products;
    }

    @Entity(tableName = DBConstant.DATABASE_TABLE.PRODUCT_DETAIL)
    public static class ProductsDTO {
        @PrimaryKey
        @SerializedName("id")
        private Integer serverId;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("price")
        private Integer price;
        @SerializedName("discountPercentage")
        private Double discountPercentage;
        @SerializedName("rating")
        private Double rating;
        @SerializedName("stock")
        private Integer stock;
        @SerializedName("brand")
        private String brand;
        @SerializedName("category")
        private String category;
        @SerializedName("thumbnail")
        private String thumbnail;

        public Integer getServerId() {
            return serverId;
        }

        public void setServerId(Integer serverId) {
            this.serverId = serverId;
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

       /* @BindingAdapter({ "thumbnail"})
        public static void loadImage(ImageView imageView, String imageURL) {
            Glide.with(imageView.getContext())
                    .setDefaultRequestOptions(new RequestOptions()
                            .circleCrop())
                    .load(imageURL)
                    .placeholder(R.drawable.rounded_drawable)
                    .into(imageView);
        }*/
    }
}
