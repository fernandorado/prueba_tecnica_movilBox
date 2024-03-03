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
    private val dao: ProductDao
) {


    suspend fun getAllProductFromApi(): List<Product> {
        val response: List<ProductModel> = api.getProducts()
        return response.map { it.toDomain() }
    }
    /*suspend fun getAllProductFromApi(): List<ProductModel> {
        val response = api.getProducts()
        ProductProvider.products = response
        return response
    }*/

    suspend fun getAllProductFromDatabase(): List<Product> {
        val response: List<ProductEntity> = dao.getAll()
        return response.map { it.toDomain() }
    }


    suspend fun insertProduct(product: List<ProductEntity>) {
        dao.insertAll(product)
    }


    suspend fun clearProducts() {
        dao.deleteProducts()
    }

    suspend fun clearProduct(product: ProductEntity) {
        dao.delete(product)
    }


}