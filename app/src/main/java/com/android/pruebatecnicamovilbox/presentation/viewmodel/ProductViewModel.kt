package com.android.pruebatecnicamovilbox.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pruebatecnicamovilbox.data.ProductRepository
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
    private val getProductDBUseCase: GetProductDBUseCase,
    private val productRepository: ProductRepository
) : ViewModel() {

    val Product = MutableLiveData<Product>()

    private val _productList = MutableLiveData<List<Product>>()
    private val _categoriresList = MutableLiveData<List<String>>()
    val productList: LiveData<List<Product>> get() = _productList
    val categoriresList: LiveData<List<String>> get() = _categoriresList
    var listaCultivos: List<Product>? = null


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

    fun searchProducts(query: String) {
        Log.d("Tag","query: $query")
        viewModelScope.launch {
            if (query.isNullOrEmpty()) {
                // Si la consulta de búsqueda es nula o está vacía, llamar a fetchProductList() para obtener todos los productos
                fetchProductList()
            } else {
                val productListAll = getProductDBUseCase.invoke()
                _productList.value = productListAll
                _productList.value?.let { productList ->

                    val filteredList = productList.filter { product ->
                        product.title.contains(query, ignoreCase = true) ||
                                product.description.contains(query, ignoreCase = true) ||
                                product.category.contains(query, ignoreCase = true) ||
                                product.brand.contains(query, ignoreCase = true)

                    }

                    // Actualizar la lista de productos filtrada
                    _productList.value = filteredList
                }
            }
        }
    }

    fun applyFilter(query:String){
        viewModelScope.launch {
            if (query.isNullOrEmpty()) {
                // Si la consulta de búsqueda es nula o está vacía, llamar a fetchProductList() para obtener todos los productos
                fetchProductList()
            } else {
                val productListAll = getProductDBUseCase.invoke()
                _productList.value = productListAll
                _productList.value?.let { productList ->

                    val filteredList = productList.filter { product ->
                                product.category.contains(query, ignoreCase = true)
                    }

                    // Actualizar la lista de productos filtrada
                    _productList.value = filteredList
                }
            }
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            try {
                val categoriresList = getProductDBUseCase.getCategories()
                _categoriresList.value = categoriresList
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching product list", e)
            }
        }
    }


}


