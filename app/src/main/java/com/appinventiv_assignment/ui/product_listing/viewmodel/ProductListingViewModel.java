package com.appinventiv_assignment.ui.product_listing.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.appinventiv_assignment.model.ProductListModel;
import com.appinventiv_assignment.ui.product_listing.repository.ProductListRepository;
import com.appinventiv_assignment.utils.network_utils.Resource;
import com.appinventiv_assignment.utils.livedata.SingleLiveEvent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListingViewModel extends ViewModel {
    MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> listOfProduct;
    @Inject
    ProductListRepository productListRepository;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    @Inject
    public ProductListingViewModel(ProductListRepository productListRepository){
        listOfProduct = new SingleLiveEvent<>();
        this.productListRepository = productListRepository;
        databaseWriteExecutor.shutdown();
    }

    public MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> getProductList(){
        return listOfProduct;
    }

    public void callProductListApi(){
        productListRepository.callProductApi(listOfProduct);
    }

    public void applyFilterOnProductDb(int position) {
        productListRepository.applyFilterOnProductDb(listOfProduct, position);
    }
}
