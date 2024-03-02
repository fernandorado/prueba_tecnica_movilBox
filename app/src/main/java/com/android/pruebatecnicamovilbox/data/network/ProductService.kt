package com.android.pruebatecnicamovilbox.data.network

import com.android.pruebatecnicamovilbox.core.RetrofitHelper
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductService @Inject constructor(private val api:ProductApiClient) {

    suspend fun getProducts(): List<ProductModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllProducts()
            response.body() ?: emptyList()
        }
    }
}