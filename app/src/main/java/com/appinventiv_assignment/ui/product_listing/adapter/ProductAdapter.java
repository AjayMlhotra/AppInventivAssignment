package com.appinventiv_assignment.ui.product_listing.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.appinventiv_assignment.R;
import com.appinventiv_assignment.databinding.AdapterProductListingBinding;
import com.appinventiv_assignment.model.ProductListModel;
import com.appinventiv_assignment.utils.AppUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductListModel.ProductsDTO> lisOfProducts;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterProductListingBinding employeeListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.adapter_product_listing, parent, false);
        return new ProductViewHolder(employeeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductViewHolder) {
            ((ProductViewHolder) holder).bind(lisOfProducts.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return lisOfProducts != null ? lisOfProducts.size() : 0;
    }

    public void submitList(List<ProductListModel.ProductsDTO> lisOfProducts) {
        this.lisOfProducts = lisOfProducts;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final AdapterProductListingBinding binding;

        public ProductViewHolder(AdapterProductListingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(ProductListModel.ProductsDTO productDetail) {
            binding.tvTitle.setText(AppUtils.isEmpty(productDetail.getBrand()) +
                    " - " + AppUtils.isEmpty(productDetail.getTitle()));
            binding.tvDiscount.setText(productDetail.getDiscountPercentage() + "% Off");
            binding.tvPrice.setText(productDetail.getPrice().toString() + " AED");
            binding.tvDesc.setText(AppUtils.isEmpty(productDetail.getDescription()));
            Glide.with(binding.imgProduct.getContext())
                    .setDefaultRequestOptions(new RequestOptions()
                            .circleCrop())
                    .load(productDetail.getThumbnail())
                    .placeholder(R.drawable.rounded_drawable)
                    .into(binding.imgProduct);
        }
    }
}

