package com.example.umc_week2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_week2.databinding.ItemProductBinding

class ProductAdapter(
    initialList: List<ProductData> = emptyList(),
    private val onHeartClick: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val productList = mutableListOf<ProductData>()

    init {
        productList.addAll(initialList)
    }

    fun submitList(list: List<ProductData>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData, position: Int) {
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

            binding.tvBestSeller.visibility =
                if (product.isBestSeller) View.VISIBLE else View.GONE

            if (product.showWishIcon) {
                binding.ivWish.visibility = View.VISIBLE

                if (product.isLiked) {
                    binding.ivWish.setImageResource(R.drawable.ic_heart_filled)
                } else {
                    binding.ivWish.setImageResource(R.drawable.ic_heart_outline)
                }

                binding.ivWish.setOnClickListener {
                    onHeartClick?.invoke(position)
                }
            } else {
                binding.ivWish.visibility = View.GONE
                binding.ivWish.setOnClickListener(null)
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
        holder.bind(productList[position], position)
    }

    override fun getItemCount(): Int = productList.size
}