package com.zfuncode.login_oauth2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zfuncode.login_oauth2.R
import com.zfuncode.login_oauth2.data.response.ProductResponseItem
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter():RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var dataProduct : List<ProductResponseItem>? = null

    fun setDataFilm(product : List<ProductResponseItem>){
        this.dataProduct = product
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return  ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.itemView.nameProduct.text = dataProduct!![position].name
        holder.itemView.categoryProduct.text = dataProduct!![position].category
        holder.itemView.priceProduct.text = dataProduct!![position].basePrice.toString()


    }

    override fun getItemCount(): Int {
        if (dataProduct == null){
            return 0
        }else{
            return  dataProduct!!.size
        }
    }
}