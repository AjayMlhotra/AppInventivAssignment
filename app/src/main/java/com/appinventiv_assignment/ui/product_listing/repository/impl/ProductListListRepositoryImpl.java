package com.appinventiv_assignment.ui.product_listing.repository.impl;

import androidx.lifecycle.MutableLiveData;
import com.appinventiv_assignment.R;
import com.appinventiv_assignment.api.ApiService;
import com.appinventiv_assignment.db.AppDatabase;
import com.appinventiv_assignment.model.ProductListModel;
import com.appinventiv_assignment.ui.product_listing.repository.ProductListRepository;
import com.appinventiv_assignment.utils.network_utils.ApiServiceOperator;
import com.appinventiv_assignment.utils.network_utils.Resource;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;

public class ProductListListRepositoryImpl implements ProductListRepository {
    private ApiService apiService;
    private final AppDatabase appDatabase;

    @Inject
    public ProductListListRepositoryImpl(ApiService apiService, AppDatabase appDatabase) {
        this.apiService = apiService;
        this.appDatabase = appDatabase;
    }
    @Override
    public void callProductApi(MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> liveData) {
        liveData.postValue(Resource.loading());
        Call<ProductListModel> call = apiService.getProductList();
        call.enqueue(new ApiServiceOperator<>(new ApiServiceOperator.OnResponseListener<>() {
            @Override
            public void onSuccess(ProductListModel body) {
                appDatabase.productDao().insertAll(body.getProducts());
                liveData.postValue(Resource.success(appDatabase.productDao().getAllProducts()));
            }

            @Override
            public void onFailure(Throwable t, String message) {
                liveData.postValue(Resource.error(t, message));
            }
        }
        ));
    }
    @Override
    public void applyFilterOnProductDb(MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> liveData, int resource) {
        if (resource == R.id.actionOdd) {
            liveData.postValue(Resource.success(appDatabase.productDao().getProductsWithOddId()));
        } else if (resource == R.id.actionDesc) {
            liveData.postValue(Resource.success(appDatabase.productDao().getProductsInDesOrder()));
        } else if (resource == R.id.actionEvenDesc) {
            liveData.postValue(Resource.success(appDatabase.productDao().getEvenProductsInDes()));
        } else if (resource == R.id.actioniPhone) {
            liveData.postValue(Resource.success(appDatabase.productDao().getAppleProducts()));
        } else if (resource == R.id.actionSamsung) {
            liveData.postValue(Resource.success(appDatabase.productDao().getSamsungProducts()));
        } else {
            liveData.postValue(Resource.success(appDatabase.productDao().getAllProducts()));
        }
    }
}

