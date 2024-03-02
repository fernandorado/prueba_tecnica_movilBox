package com.android.pruebatecnicamovilbox.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.android.pruebatecnicamovilbox.databinding.FragmentProductListBinding
import com.android.pruebatecnicamovilbox.domain.model.Product
import com.android.pruebatecnicamovilbox.presentation.adapters.AdaptadorProduct
import com.android.pruebatecnicamovilbox.presentation.viewmodel.ProductViewModel



class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    val productViewModel: ProductViewModel by viewModels()

    lateinit var adapter: AdaptadorProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataset = arrayOf(
            Product(
                id = 1,
                title = "iPhone 9",
                description = "An apple mobile which is nothing like apple",
                price = 549.0,
                discountPercentage = 12.96,
                rating = 4.69,
                stock = 94,
                brand = "Apple",
                category = "smartphones",
                thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
                images = listOf(
                    "https://cdn.dummyjson.com/product-images/1/1.jpg",
                    "https://cdn.dummyjson.com/product-images/1/2.jpg",
                    "https://cdn.dummyjson.com/product-images/1/3.jpg",
                    "https://cdn.dummyjson.com/product-images/1/4.jpg",
                    "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
                )
            ),
            Product(
                id = 2,
                title = "iPhone X",
                description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
                price = 899.0,
                discountPercentage = 17.94,
                rating = 4.44,
                stock = 34,
                brand = "Apple",
                category = "smartphones",
                thumbnail = "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg",
                images = listOf(
                    "https://cdn.dummyjson.com/product-images/2/1.jpg",
                    "https://cdn.dummyjson.com/product-images/2/2.jpg",
                    "https://cdn.dummyjson.com/product-images/2/3.jpg",
                    "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg"
                )
            ),
            Product(
                id = 3,
                title = "Samsung Universe 9",
                description = "Samsung's new variant which goes beyond Galaxy to the Universe",
                price = 1249.0,
                discountPercentage = 15.46,
                rating = 4.09,
                stock = 36,
                brand = "Samsung",
                category = "smartphones",
                thumbnail = "https://cdn.dummyjson.com/product-images/3/thumbnail.jpg",
                images = listOf(
                    "https://cdn.dummyjson.com/product-images/3/1.jpg"
                )
            )
        )


        //adapter = AdaptadorProduct(productViewModel.listaCultivos!!)

        Log.d("TAG", "productViewModel.listaCultivos: $productViewModel.listaCultivos!!")
        adapter = productViewModel.listaCultivos?.let { AdaptadorProduct(productViewModel.listaCultivos!!) } ?: AdaptadorProduct(emptyList())

        productViewModel.productList.observe(viewLifecycleOwner) { productList ->
            // Actualiza el adaptador con la nueva lista de productos
            adapter.updateProductList(productList)
        }
        //adapter = AdaptadorProduct(productViewModel.fetchProductList())

        Log.d("TAG", "Entro a onViewCreated ")
        //productViewModel.fetchProductList()
        //adapter = AdaptadorProduct(productViewModel.listaCultivos!!)
        val recyclerView = binding.recyclerProduct
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        productViewModel.productList.observe(viewLifecycleOwner) { productList ->
            // Actualiza el adaptador con la nueva lista de productos
            adapter.updateProductList(productList)
        }

        //productViewModel.fetchProductList()
    }
}
