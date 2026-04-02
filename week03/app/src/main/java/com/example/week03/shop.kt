package com.example.week03

import ProductAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week03.databinding.FragmentShopBinding
import com.google.android.gms.analytics.ecommerce.Product

class ShopFragment : Fragment(R.layout.fragment_shop) {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentShopBinding.bind(view)

        // 🔹 더미 데이터
        val productList = listOf(
            Product("Nike Everyday Plus Cusioned", "US$10", R.drawable.socks),
            Product("상품2", "US$16", R.drawable.socks),
            Product("상품3", "US$115", R.drawable.socks),
            Product("상품4", "US$115", R.drawable.socks)
        )


        binding.shopRecyclerview.layoutManager =
            GridLayoutManager(requireContext(), 2)


        binding.shopRecyclerview.adapter = ProductAdapter(productList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}