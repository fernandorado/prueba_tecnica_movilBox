package com.android.pruebatecnicamovilbox.domain

import com.android.pruebatecnicamovilbox.data.ProductRepository
import com.android.pruebatecnicamovilbox.data.database.entities.ProductEntity
import com.android.pruebatecnicamovilbox.data.database.entities.toDatabase
import com.android.pruebatecnicamovilbox.domain.model.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke():List<Product>{
        val product = repository.getAllProductFromApi()

        return if(product.isNotEmpty()){
            repository.clearProducts()
            repository.insertProduct(product.map { it.toDatabase() })
            product
        }else{
            repository.getAllProductFromDatabase()
        }
    }
}