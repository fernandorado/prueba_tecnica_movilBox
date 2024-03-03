package com.android.pruebatecnicamovilbox.data

import android.util.Log
import com.android.pruebatecnicamovilbox.data.database.dao.ProductDao
import com.android.pruebatecnicamovilbox.data.database.entities.ProductEntity
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.data.model.ProductProvider
import com.android.pruebatecnicamovilbox.data.network.ProductService
import com.android.pruebatecnicamovilbox.domain.model.Product
import com.android.pruebatecnicamovilbox.domain.model.toDomain
import javax.inject.Inject

class ProductRepository {

    private val api = ProductService()

    suspend fun getAllProductFromApi(): List<ProductModel> {
        val response: List<ProductModel> = api.getProducts()
        return response.map { it.toDomain() }
    }
    /*suspend fun getAllProductFromApi(): List<ProductModel> {
        val response = api.getProducts()
        ProductProvider.products = response
        return response
    }*/
    /*
            suspend fun getAllProductFromDatabase():List<ProductModel>{
                val response: List<ProductModel> = dao.getAll()
                return response.map { it.toDomain() }
            }
        /*

     */
                suspend fun insertProduct(product:List<ProductEntity>){
                    productDao.insertAll(product)
                }

                suspend fun clearProducts(){
                    productDao.deleteProducts()
                }

                suspend fun clearProduct(product: ProductEntity){
                    productDao.delete(product)
                }
            */
}