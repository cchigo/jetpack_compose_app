package com.chichi.productlistapp.di

import android.content.Context
import androidx.room.Room
import com.chichi.productlistapp.data.local.ProductsDatabase
import com.chichi.productlistapp.util.Constants.PRODUCT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ProductsDatabase {
        return Room.databaseBuilder(
            context,
            ProductsDatabase::class.java,
            PRODUCT_DATABASE
        ).build()
    }

}