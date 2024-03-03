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
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductDBUseCase: GetProductDBUseCase
): ViewModel() {

    val productModel = MutableLiveData<Product>()
    val isLoading = MutableLiveData<Boolean>()

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    var listaCultivos: List<ProductModel>? = null
    val _selectedProduct = MutableLiveData<ProductModel>()

    // Expone selectedProduct como LiveData público
    val selectedProduct: LiveData<ProductModel> get() = _selectedProduct

    fun selectProduct(product: ProductModel) {
        _selectedProduct.value = product
    }


    init {
        fetchProductList()
        selectedProduct.observeForever { product ->
            if (product == null) {
                Log.d("ProductViewModel", "selectedProduct está vacío ")
            } else {
                Log.d("ProductViewModel", "selectedProduct no está vacío ${selectedProduct.value}")
            }
        }
    }

    private fun fetchProductList() {
        viewModelScope.launch {
            try {
                val productList = getProductDBUseCase.invoke()
                _productList.value = productList
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching product list", e)
            }
        }
    }


}


