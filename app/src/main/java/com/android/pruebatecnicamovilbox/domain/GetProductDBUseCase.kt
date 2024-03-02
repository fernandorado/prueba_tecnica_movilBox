package com.android.pruebatecnicamovilbox.domain


import com.android.pruebatecnicamovilbox.data.ProductRepository
import com.android.pruebatecnicamovilbox.domain.model.Product
import javax.inject.Inject

class GetProductDBUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(): List<Product> {
        return repository.getAllProductFromDatabase()
    }
}