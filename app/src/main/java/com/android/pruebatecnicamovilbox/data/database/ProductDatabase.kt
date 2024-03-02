package com.android.pruebatecnicamovilbox.data.database

import androidx.room.Database;
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.pruebatecnicamovilbox.data.database.dao.ProductDao
import com.android.pruebatecnicamovilbox.data.database.entities.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
@TypeConverters(Converters::class) // Registrar el convertidor de tipo aquí
abstract class ProductDatabase: RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}
