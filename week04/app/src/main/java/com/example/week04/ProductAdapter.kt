package com.example.week04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var productList: List<Product>,
    private val onHeartClick: (List<Product>) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.item_image)
        val name = itemView.findViewById<TextView>(R.id.item_name)
        val price = itemView.findViewById<TextView>(R.id.item_price)
        val heart = itemView.findViewById<ImageView>(R.id.item_wish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.image.setImageResource(product.imageRes)
        holder.name.text = product.name
        holder.price.text = product.price

        holder.heart.setImageResource(
            if (product.isLiked) R.drawable.hearton
            else R.drawable.heartoff
        )

        holder.heart.setOnClickListener {
            product.isLiked = !product.isLiked
            notifyItemChanged(position)
            onHeartClick(productList)
        }
    }

    override fun getItemCount(): Int = productList.size
}