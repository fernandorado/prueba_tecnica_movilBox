package com.android.pruebatecnicamovilbox.domain

import com.android.pruebatecnicamovilbox.data.ProductRepository
import com.android.pruebatecnicamovilbox.data.database.entities.toDatabase
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.domain.model.Product
import com.android.pruebatecnicamovilbox.domain.model.toDomain
import javax.inject.Inject

class GetProductsUseCase  @Inject constructor(private val repository:ProductRepository) {

    suspend operator fun invoke():List<Product>{
        val quotes = repository.getAllProductFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearProducts()
            repository.insertProduct(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllProductFromDatabase()
        }
    }
}