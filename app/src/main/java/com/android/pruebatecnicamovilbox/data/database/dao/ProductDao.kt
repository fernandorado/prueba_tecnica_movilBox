package com.android.pruebatecnicamovilbox.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.pruebatecnicamovilbox.data.database.entities.ProductEntity

@Dao
interface ProductDao {

    //CONSULTA
    @Query("SELECT * FROM products ORDER BY rating DESC")
    suspend fun getAll():List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: Int): ProductEntity

    @Query("SELECT * FROM products WHERE category = :category ORDER BY category DESC")
    suspend fun getByCategory(category: String):List<ProductEntity>

    @Query("SELECT * FROM products ORDER BY price DESC")
    suspend fun getByPrice():List<ProductEntity>

    @Query("SELECT * FROM products ORDER BY rating DESC")
    suspend fun getByRating():List<ProductEntity>

    @Query("SELECT * FROM products ORDER BY discountPercentage DESC")
    suspend fun getByDescount():List<ProductEntity>

    @Query("SELECT * FROM products ORDER BY stock DESC")
    suspend fun getByStock():List<ProductEntity>

    @Query("SELECT * FROM products WHERE category = :brand ORDER BY brand DESC")
    suspend fun getByBrand(brand: String):List<ProductEntity>

    //ACTUALIZAR
    @Update
    suspend fun update(productEntity: ProductEntity)
    //INSERTAR
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)
    //ELIMINAR
    @Delete
    suspend fun delete(product: ProductEntity)
    @Query("DELETE FROM products")
    suspend fun deleteProducts()

}