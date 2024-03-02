package com.android.pruebatecnicamovilbox.data

import com.android.pruebatecnicamovilbox.data.database.dao.ProductDao
import com.android.pruebatecnicamovilbox.data.database.entities.ProductEntity
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.data.network.ProductService
import com.android.pruebatecnicamovilbox.domain.model.Product
import com.android.pruebatecnicamovilbox.domain.model.toDomain
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductService,
    private val productDao: ProductDao
){
    suspend fun getAllProductFromApi(): List<Product> {
        val response: List<ProductModel> = api.getProducts()
        return response.map { it.toDomain() }
    }

    suspend fun getAllProductFromDatabase():List<Product>{
        val response: List<ProductEntity> = productDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun insertProduct(product:List<ProductEntity>){
        productDao.insertAll(product)
    }

    suspend fun clearProducts(){
        productDao.deleteProducts()
    }

    suspend fun clearProduct(product: ProductEntity){
        productDao.delete(product)
    }

}