package com.android.pruebatecnicamovilbox.data.network

import com.android.pruebatecnicamovilbox.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiClient {
    @GET("/.json")
    suspend fun getAllProducts(): Response<List<ProductModel>>
}