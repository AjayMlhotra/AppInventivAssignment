package com.appinventiv_assignment.ui.product_listing.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.appinventiv_assignment.model.ProductListModel;
import com.appinventiv_assignment.ui.product_listing.repository.ProductListRepository;
import com.appinventiv_assignment.ui.product_listing.repository.impl.ProductListListRepositoryImpl;
import com.appinventiv_assignment.utils.network_utils.Resource;
import com.appinventiv_assignment.utils.livedata.SingleLiveEvent;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListingViewModel extends ViewModel {
    private final MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> listOfProduct;
    private ProductListRepository productListRepository;
    @Inject
    public ProductListingViewModel(ProductListListRepositoryImpl productListRepositoryImpl){
        listOfProduct = new SingleLiveEvent<>();
        productListRepository = productListRepositoryImpl;
    }

    public MutableLiveData<Resource<List<ProductListModel.ProductsDTO>>> getProductList(){
        return listOfProduct;
    }

    public void callProductListApi(){
        productListRepository.callProductApi(listOfProduct);
    }

    public void applyFilterOnProductDb(int resourceId) {
        productListRepository.applyFilterOnProductDb(listOfProduct, resourceId);
    }
}
