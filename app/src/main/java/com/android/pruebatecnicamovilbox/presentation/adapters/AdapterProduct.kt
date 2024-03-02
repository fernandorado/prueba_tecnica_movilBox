package com.android.pruebatecnicamovilbox.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.pruebatecnicamovilbox.R
import com.android.pruebatecnicamovilbox.data.model.ProductModel
import com.android.pruebatecnicamovilbox.domain.model.Product
import com.bumptech.glide.Glide

class AdaptadorProduct(private var productList: List<ProductModel>) :
    RecyclerView.Adapter<AdaptadorProduct.ViewHolderProduct>() {

    lateinit var vgrupo: ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduct {
        vgrupo = parent
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        return ViewHolderProduct(vista)
    }

    fun updateProductList(newProductList: List<ProductModel>) {
        productList = newProductList
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }

    override fun onBindViewHolder(holder: ViewHolderProduct, position: Int) {
        val product = productList[position]

        holder.txtName.text = product.title
        holder.txtBrand.text = product.brand
        holder.txtPrice.text = "$"+product.price
        holder.txtRating.text = product.rating.toString()

        // Aquí seleccionamos la primera URL de la lista de imágenes del producto
        val imageUrl = product.images.firstOrNull() // O elige otra lógica para seleccionar la URL adecuada

        Glide.with(vgrupo.context)
            .load(imageUrl)
            .error(R.drawable.imagenrota) // Agrega una imagen de error opcional
            .into(holder.imgProduct)

        /*
        holder.menuPopUp.setOnClickListener {
            // Implementa el menú emergente aquí
        }
        */
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolderProduct(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView
        val txtName: TextView
        val txtBrand: TextView
        val txtPrice: TextView
        val txtRating: TextView
        val menuPopUp: ImageView
        init{
            imgProduct = itemView.findViewById(R.id.idImgProduct)
            txtName = itemView.findViewById(R.id.idNameProduct)
            txtBrand= itemView.findViewById(R.id.idBrandProduct)
            txtPrice= itemView.findViewById(R.id.idPriceProduct)
            txtRating = itemView.findViewById(R.id.idRatingProduct)
            menuPopUp= itemView.findViewById(R.id.menuOpciones)
        }
    }
}
