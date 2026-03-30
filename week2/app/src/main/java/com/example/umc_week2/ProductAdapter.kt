package com.example.umc_week2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week2.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductData>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData) {

            binding.ivProduct.setImageResource(product.imageResId)
            binding.tvProductName.text = product.name
            binding.tvPrice.text = product.price

            if (product.category.isBlank()) {
                binding.tvCategory.visibility = View.GONE
            } else {
                binding.tvCategory.visibility = View.VISIBLE
                binding.tvCategory.text = product.category
            }

            if (product.subInfo.isBlank()) {
                binding.tvSubInfo.visibility = View.GONE
            } else {
                binding.tvSubInfo.visibility = View.VISIBLE
                binding.tvSubInfo.text = product.subInfo
            }

            if (product.isBestSeller) {
                binding.tvBestSeller.visibility = View.VISIBLE
            } else {
                binding.tvBestSeller.visibility = View.GONE
            }

            if (product.showWishIcon) {
                binding.ivWish.visibility = View.VISIBLE

                if (product.isLiked) {
                    binding.ivWish.setImageResource(R.drawable.ic_heart_filled)
                } else {
                    binding.ivWish.setImageResource(R.drawable.ic_heart_outline)
                }
            } else {
                binding.ivWish.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
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