package com.android.pruebatecnicamovilbox.data.network

import com.android.pruebatecnicamovilbox.data.model.ProductListResponse
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.domain.model.Product
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiClient {
    @GET("/products")
    suspend fun getAllProducts(): ProductListResponse
    @GET("/products/categories")
    suspend fun getAllCategorires(): List<String>
}



