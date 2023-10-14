package com.appinventiv_assignment.ui.product_listing.repository;

import androidx.lifecycle.MutableLiveData;

import com.appinventiv_assignment.api.ApiService;
import com.appinventiv_assignment.db.AppDatabase;
import com.appinventiv_assignment.model.ProductListModel;
import com.appinventiv_assignment.utils.network_utils.ApiServiceOperator;
import com.appinventiv_assignment.utils.network_utils.Resource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class ProductListRepository {
    private ApiService apiService;

    private final AppDatabase appDatabase;

    @Inject
    public ProductListRepository(ApiService apiService, AppDatabase appDatabase) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
    }

    public void callProductApi(MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> liveData) {
        liveData.postValue(Resource.loading());
        Call<ProductListModel> call = apiService.getProductList();
        call.enqueue(new ApiServiceOperator<>(new ApiServiceOperator.OnResponseListener<>() {
            @Override
            public void onSuccess(ProductListModel body) {
                appDatabase.productDao().insertAll(body.getProducts());
                liveData.postValue(Resource.success(appDatabase.productDao().getAll1()));
            }

            @Override
            public void onFailure(Throwable t, String message) {
                liveData.postValue(Resource.error(t, message));
            }
        }
        ));
    }

    public void applyFilterOnProductDb(MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> liveData, int position) {
        liveData.postValue(Resource.success(appDatabase.productDao().getAll(position)));
    }
}

