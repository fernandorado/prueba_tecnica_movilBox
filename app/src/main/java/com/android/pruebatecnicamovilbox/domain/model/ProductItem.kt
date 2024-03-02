package com.android.pruebatecnicamovilbox.domain.model

import com.android.pruebatecnicamovilbox.data.database.entities.ProductEntity
import com.android.pruebatecnicamovilbox.data.model.ProductModel

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)

fun ProductModel.toDomain() = ProductModel(id, title, description, price,discountPercentage,rating, stock, brand, category, thumbnail, images)
fun ProductEntity.toDomain() = Product(id, title, description, price,discountPercentage,rating, stock, brand, category, thumbnail, images)
