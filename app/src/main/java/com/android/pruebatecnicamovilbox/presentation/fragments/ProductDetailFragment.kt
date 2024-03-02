package com.android.pruebatecnicamovilbox.presentation.fragments

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.android.pruebatecnicamovilbox.R
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.databinding.FragmentProductDetailBinding
import com.android.pruebatecnicamovilbox.presentation.viewmodel.ProductViewModel
import com.denzcoskun.imageslider.models.SlideModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = arguments?.getParcelable<ProductModel>("product")
        Log.d("PRODUCT DETAIL", "PRODUCTOS no está vacío $product")
        binding.txtNameProduct.text = product!!.title
        binding.txtRaitingProduct.text = product!!.rating.toString()
        binding.txtBrandProduct.text = product!!.brand
        binding.txtDiscountProduct.text = "-" + product!!.discountPercentage + "%"
        binding.txtDescriptionProduct.text = product!!.description
        binding.txtStockProduct.text = product!!.stock.toString()
        // Precio con descuento
        val precioTotal = product!!.price - (product!!.price * (product!!.discountPercentage / 100))
        binding.txtPriceDescountProduct.text = precioTotal.toString()
        //TACHADO
        val spannableString = SpannableString(product!!.price.toString())
        spannableString.setSpan(StrikethroughSpan(), 0, product!!.price.toString().length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        binding.txtPriceProduct.text = spannableString

        val imageList = ArrayList<SlideModel>()
        for (imageUrl in product!!.images) {
            imageList.add(SlideModel(imageUrl))
        }
        
        binding.imageSlider.setImageList(imageList)
    }
}
