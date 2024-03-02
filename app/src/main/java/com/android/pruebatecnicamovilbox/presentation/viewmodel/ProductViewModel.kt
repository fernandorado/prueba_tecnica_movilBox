package com.android.pruebatecnicamovilbox.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pruebatecnicamovilbox.domain.GetProductsUseCase
import com.android.pruebatecnicamovilbox.domain.GetProductDBUseCase
import com.android.pruebatecnicamovilbox.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductsUseCase,
    private val getProductDBUseCase: GetProductDBUseCase
) : ViewModel() {

    val productModel = MutableLiveData<Product>()
    val isLoading = MutableLiveData<Boolean>()
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    var listaCultivos: List<Product>? = null

    fun fetchProductList() {
        Log.d("TAG", "Entro a fetchProductList ")
        viewModelScope.launch {
            try {
                val productList = getProductDBUseCase()
                listaCultivos = productList
                _productList.value = productList
            } catch (e: Exception) {
                // Maneja el error de forma apropiada
            }
        }
    }

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getProductUseCase()

            if (!result.isNullOrEmpty()) {
                productModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }




}


