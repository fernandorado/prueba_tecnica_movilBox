package com.android.pruebatecnicamovilbox.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.pruebatecnicamovilbox.R
import com.android.pruebatecnicamovilbox.databinding.FragmentProductListBinding
import com.android.pruebatecnicamovilbox.domain.model.Product
import com.android.pruebatecnicamovilbox.presentation.adapters.AdaptadorProduct
import com.android.pruebatecnicamovilbox.presentation.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(), AdaptadorProduct.OnProductClickListener {

    private lateinit var binding: FragmentProductListBinding
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var adapter: AdaptadorProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel.onCreate()


        adapter = productViewModel.listaCultivos?.let {
            AdaptadorProduct(
                productViewModel.listaCultivos!!,
                this
            )
        }
            ?: AdaptadorProduct(emptyList(), this)

        productViewModel.productList.observe(viewLifecycleOwner) { productList ->
            // Actualiza el adaptador con la nueva lista de productos
            adapter.updateProductList(productList)
        }

        val recyclerView = binding.recyclerProduct
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        binding.searchIcon.setOnClickListener {
            binding.searchView.visibility = View.VISIBLE
            binding.searchIcon.visibility = View.GONE
            val listener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Realizar búsqueda cuando se presiona Enter en el teclado
                    query?.let { productViewModel.searchProducts(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Realizar búsqueda mientras se escribe en la barra de búsqueda
                    newText?.let { productViewModel.searchProducts(it) }
                    return true
                }
            }

            binding.searchView.setOnQueryTextListener(listener)
        }


    }

    override fun onProductClick(product: Product) {
        val bundle = Bundle().apply {
            putParcelable("product", product)
        }
        findNavController().navigate(
            R.id.action_producListFragment_to_productDetailFragment,
            bundle
        )
    }
}
