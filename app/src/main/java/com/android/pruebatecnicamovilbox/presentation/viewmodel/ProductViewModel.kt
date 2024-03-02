package com.android.pruebatecnicamovilbox.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.domain.GetProductsUseCase
import com.android.pruebatecnicamovilbox.domain.GetProductDBUseCase
import com.android.pruebatecnicamovilbox.domain.model.Product

import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class ProductViewModel : ViewModel() {

    private val getProductUseCase = GetProductsUseCase()

    val productModel = MutableLiveData<Product>()
    val isLoading = MutableLiveData<Boolean>()
    private val _productList = MutableLiveData<List<ProductModel>>()
    val productList: LiveData<List<ProductModel>> get() = _productList
    var listaCultivos: List<ProductModel>? = null

    init {
        fetchProductList()
    }

    private fun fetchProductList() {
        viewModelScope.launch {
            try {
                val productList = getProductUseCase.invoke()
                _productList.value = productList
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching product list", e)
            }
        }
    }

    /*
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

     */


}

