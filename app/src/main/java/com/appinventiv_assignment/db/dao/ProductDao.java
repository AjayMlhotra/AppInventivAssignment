package com.appinventiv_assignment.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.appinventiv_assignment.model.ProductListModel;
import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductListModel.ProductsDTO> productModels);
    @Query("SELECT * from PRODUCT_DETAIL where serverId %2 == 1")
    List<ProductListModel.ProductsDTO> getProductsWithOddId();

    @Query("SELECT * from PRODUCT_DETAIL order by serverId DESC")
    List<ProductListModel.ProductsDTO> getProductsInDesOrder();

    @Query("SELECT * from PRODUCT_DETAIL where serverId % 2 == 0 order by serverId DESC")
    List<ProductListModel.ProductsDTO> getEvenProductsInDes();

    @Query("select * from PRODUCT_DETAIL where brand == 'Apple' AND category == 'smartphones'")
    List<ProductListModel.ProductsDTO> getAppleProducts();

    @Query("SELECT * from PRODUCT_DETAIL where brand == 'Samsung'")
    List<ProductListModel.ProductsDTO> getSamsungProducts();

    @Query("SELECT * FROM PRODUCT_DETAIL")
    List<ProductListModel.ProductsDTO> getAllProducts();
}