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
public class ProductListingFragment extends Fragment implements ProductAdapter.OnItemClickListener {
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
        setRecyclerView();
        setObserver();
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
        mProductAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(mProductAdapter);
    }

    private void handleObserver(String apiType, Resource resource) {
        if (resource.getType() == Status.LOADING) {
            //show loader
            AppUtils.showToast(requireContext(), "Loader");
        } else if (resource.getType() == Status.SUCCESS) {
            if (apiType.equals(ApiConstant.PRODUCT_LIST)) {
                AppUtils.showToast(requireContext(), "Data Fetched Successfully");
                List<ProductListModel.ProductsDTO> mListOfProducts = (List<ProductListModel.ProductsDTO>) resource.getData();
                if (mListOfProducts != null && !mListOfProducts.isEmpty()) {
                    mProductAdapter.submitList(mListOfProducts);
                }
            }
        } else {
            AppUtils.showToast(requireContext(), resource.getMessage());
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(requireActivity(), view);
        popupMenu.inflate(R.menu.option_menu);
        popupMenu.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) menuItem -> {
            if (menuItem.getItemId() == R.id.actionOdd) {
                productListingViewModel.applyFilterOnProductDb(0);
                AppUtils.showToast(requireContext(), "Odd" + 0);
            } else if (menuItem.getItemId() == R.id.actionDesc) {
                productListingViewModel.applyFilterOnProductDb(1);
                AppUtils.showToast(requireContext(), "Descending " + position);
            } else if (menuItem.getItemId() == R.id.actionEvenDesc) {
                productListingViewModel.applyFilterOnProductDb(2);
                AppUtils.showToast(requireContext(), "Even Desc " + position);
            } else if (menuItem.getItemId() == R.id.actioniPhone) {
                productListingViewModel.applyFilterOnProductDb(3);
                AppUtils.showToast(requireContext(), "iPhone " + position);
            } else if (menuItem.getItemId() == R.id.actionSamsung) {
                productListingViewModel.applyFilterOnProductDb(4);
                AppUtils.showToast(requireContext(), "Samsung " + position);
            } else {
                productListingViewModel.applyFilterOnProductDb(5);
                AppUtils.showToast(requireContext(), "Clear All " + position);
            }
            return false;
        });
        popupMenu.show();
    }
}