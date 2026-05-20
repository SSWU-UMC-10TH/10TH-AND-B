package com.example.week03

import ProductAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week03.databinding.FragmentWishlistBinding
import com.google.android.gms.analytics.ecommerce.Product

class WishlistFragment : Fragment(R.layout.fragment_wishlist) {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWishlistBinding.bind(view)

        val productList = listOf(
            Product("위시1", "₩15000", R.drawable.home),
            Product("위시2", "₩25000", R.drawable.home)
        )


        binding.wishlistRecyclerview.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.wishlistRecyclerview.adapter = ProductAdapter(productList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
