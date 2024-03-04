package com.android.pruebatecnicamovilbox.data.network

import com.android.pruebatecnicamovilbox.core.RetrofitHelper
import com.android.pruebatecnicamovilbox.data.model.ProductListResponse
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductService  @Inject constructor(private val api:ProductApiClient){
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getProducts(): List<ProductModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllProducts()
            val productsResponse: ProductListResponse? = response
            productsResponse?.products ?: emptyList()
        }
    }

    suspend fun getCategories(): List<String> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCategorires()
            response ?: emptyList()
        }
    }
}