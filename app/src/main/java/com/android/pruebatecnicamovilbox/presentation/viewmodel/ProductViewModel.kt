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
): ViewModel() {

    val Product = MutableLiveData<Product>()
    val isLoading = MutableLiveData<Boolean>()

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    var listaCultivos: List<Product>? = null
    val _selectedProduct = MutableLiveData<Product>()

    // Expone selectedProduct como LiveData público
    val selectedProduct: LiveData<Product> get() = _selectedProduct

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }


    init {
        fetchProductList()
    }

    fun fetchProductList() {
        viewModelScope.launch {
            try {
                val productList = getProductDBUseCase.invoke()
                _productList.value = productList
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching product list", e)
            }
        }
    }


    fun onCreate() {
        viewModelScope.launch {
            try {
                getProductUseCase.invoke()
                val productList = getProductDBUseCase.invoke()
                _productList.value = productList
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching product list", e)
            }
        }
    }


}


