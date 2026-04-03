package com.example.umc_week2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week2.databinding.ItemHomeProductBinding

class HomeProductAdapter(
    private val productList: List<ProductData>
) : RecyclerView.Adapter<HomeProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemHomeProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData) {
            binding.ivHomeProduct.setImageResource(product.imageResId)
            binding.tvHomeProductName.text = product.name
            binding.tvHomeProductPrice.text = product.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}
