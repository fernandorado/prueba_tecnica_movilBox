package com.android.pruebatecnicamovilbox.di

import android.content.Context
import androidx.room.Room
import com.android.pruebatecnicamovilbox.data.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    private val PRODUCT_DATABASE_NAME = "product_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ProductDatabase::class.java, PRODUCT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideProductDao(db: ProductDatabase)=db.getProductDao()
}