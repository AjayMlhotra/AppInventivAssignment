package com.appinventiv_assignment.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.appinventiv_assignment.db.constant.DBConstant.DATABASE_TABLE;
import com.appinventiv_assignment.model.ProductListModel;
import java.util.List;

@Dao
public interface ProductDao {
    //SELECT * FROM table WHERE MOD(num, 2) = 1 ORDER BY id ASC;
    @Query("SELECT * FROM PRODUCT_DETAIL ORDER BY CASE WHEN :type = 0 THEN serverId % 2 = 1 END ASC, CASE WHEN :type = 1 THEN serverId END DESC")
    List<ProductListModel.ProductsDTO> getAll(int type);

    @Query("SELECT * FROM " + DATABASE_TABLE.PRODUCT_DETAIL)
    List<ProductListModel.ProductsDTO> getAll1();
   /* @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);*/


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductListModel.ProductsDTO> productModels);

    @Query("DELETE FROM " + DATABASE_TABLE.PRODUCT_DETAIL)
    void deleteAll();
}