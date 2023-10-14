package com.appinventiv_assignment.di;

import android.content.Context;

import com.appinventiv_assignment.db.AppDatabase;
import com.appinventiv_assignment.db.dao.ProductDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class DatabaseModule {
    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides
    public ProductDao provideContactDao(AppDatabase appDatabase) {
        return appDatabase.productDao();
    }
}