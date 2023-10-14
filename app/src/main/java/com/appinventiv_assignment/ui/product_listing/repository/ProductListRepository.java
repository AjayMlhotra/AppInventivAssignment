package com.appinventiv_assignment.ui.product_listing.repository;

import androidx.lifecycle.MutableLiveData;

import com.appinventiv_assignment.model.ProductListModel;
import com.appinventiv_assignment.utils.network_utils.Resource;
import java.util.List;

public interface ProductListRepository {
    void callProductApi(MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> liveData);
    void applyFilterOnProductDb(MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> liveData, int resource);
}
