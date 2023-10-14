package com.appinventiv_assignment.api;

import com.appinventiv_assignment.model.ProductListModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(ApiConstant.PRODUCT_LIST)
    Call<ProductListModel> getProductList();

}
