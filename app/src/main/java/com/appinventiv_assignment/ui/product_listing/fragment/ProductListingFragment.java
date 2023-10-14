package com.appinventiv_assignment.ui.product_listing.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appinventiv_assignment.R;
import com.appinventiv_assignment.api.ApiConstant;
import com.appinventiv_assignment.databinding.FragmentProductListingBinding;
import com.appinventiv_assignment.model.ProductListModel;
import com.appinventiv_assignment.ui.product_listing.adapter.ProductAdapter;
import com.appinventiv_assignment.ui.product_listing.viewmodel.ProductListingViewModel;
import com.appinventiv_assignment.utils.AppUtils;
import com.appinventiv_assignment.utils.network_utils.Resource;
import com.appinventiv_assignment.utils.network_utils.Status;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListingFragment extends Fragment {
    private FragmentProductListingBinding binding;
    private ProductListingViewModel productListingViewModel;
    private ProductAdapter mProductAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductListingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productListingViewModel = new ViewModelProvider(this).get(ProductListingViewModel.class);
        productListingViewModel.callProductListApi();
        setListener();
        setObserver();
        setRecyclerView();
    }

    private void setListener() {
        binding.icMenu.setOnClickListener(view -> {
            showFilterPopup(binding.icMenu);
        });
    }

    private void setObserver() {
        productListingViewModel.getProductList().observe(requireActivity(), productListModel -> {
            handleObserver(ApiConstant.PRODUCT_LIST, productListModel);
        });
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = binding.rcvProducts;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        mProductAdapter = new ProductAdapter();
        recyclerView.setAdapter(mProductAdapter);
    }

    private void handleObserver(String apiType, Resource resource) {
        if (resource.getType() == Status.LOADING) {
            //show loader
            AppUtils.showToast(requireContext(), "Loader");
        } else if (resource.getType() == Status.SUCCESS) {
            //Hide Loader
            if (apiType.equals(ApiConstant.PRODUCT_LIST)) {
                List<ProductListModel.ProductsDTO> mListOfProducts = (List<ProductListModel.ProductsDTO>) resource.getData();
                mProductAdapter.submitList(mListOfProducts);
                if(mListOfProducts == null || mListOfProducts.isEmpty()){
                    binding.tvError.setVisibility(View.VISIBLE);
                } else binding.tvError.setVisibility(View.GONE);
            }
        } else {
            //Hide Loader
            AppUtils.showToast(requireContext(), resource.getMessage());
        }
    }

    private void showFilterPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(requireActivity(), view);
        popupMenu.inflate(R.menu.option_menu);
        popupMenu.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) menuItem -> {
            productListingViewModel.applyFilterOnProductDb(menuItem.getItemId());
            return false;
        });
        popupMenu.show();
    }
}