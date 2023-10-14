package com.appinventiv_assignment.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.appinventiv_assignment.db.constant.DBConstant;
import com.appinventiv_assignment.db.dao.ProductDao;
import com.appinventiv_assignment.db.type_converter.ImageListConverter;
import com.appinventiv_assignment.model.ProductListModel;

@Database(entities = {ProductListModel.ProductsDTO.class}, version = DBConstant.VERSION, exportSchema = false)
@TypeConverters({ImageListConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context);
                }
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DBConstant.NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Log.d("TAG", "onCreate() called with: db = " + db);
                    }

                    @Override
                    public void onOpen(SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        Log.d("TAG", "onOpen() called with: db = " + db);
                    }
                })
                .allowMainThreadQueries()
                .build();
    }
}


