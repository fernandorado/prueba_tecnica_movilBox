package com.android.pruebatecnicamovilbox.domain

import com.android.pruebatecnicamovilbox.data.ProductRepository
import com.android.pruebatecnicamovilbox.data.database.entities.ProductEntity
import com.android.pruebatecnicamovilbox.data.database.entities.toDatabase
import com.android.pruebatecnicamovilbox.domain.model.Product
import javax.inject.Inject

class GetProductsUseCase {
    private val repository= ProductRepository()
    suspend operator fun invoke() = repository.getAllProductFromApi()
    //suspend operator fun invoke() = repository.getAllProductFromDatabase()
}