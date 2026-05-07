package com.example.week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week02.databinding.ItemProductBinding

class ProductAdapter(private val productList: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    // 1. 아이템 뷰(item_product.xml)를 묶어두는 뷰홀더
    inner class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.itemProductIv.setImageResource(product.imageResId)
            binding.itemProductNameTv.text = product.name
            binding.itemProductPriceTv.text = product.price
        }
    }

    // 2. 화면(뷰홀더)이 처음 만들어질 때 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 3. 총 아이템 개수 알려주기
    override fun getItemCount(): Int = productList.size

    // 4. 화면에 데이터 그리기
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}