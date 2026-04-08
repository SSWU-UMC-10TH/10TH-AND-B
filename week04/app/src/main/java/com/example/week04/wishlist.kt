package com.example.week04

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week04.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment(R.layout.fragment_wishlist) {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWishlistBinding.bind(view)
        dataStoreManager = DataStoreManager(requireContext())

        binding.wishlistRecyclerview.layoutManager =
            GridLayoutManager(requireContext(), 2)

        lifecycleScope.launch {
            dataStoreManager.getProducts().collect { list ->


                val likedList = list.filter { it.isLiked }

                val adapter = ProductAdapter(likedList) { updatedList ->
                    lifecycleScope.launch {
                        dataStoreManager.saveProducts(updatedList)
                    }
                }

                binding.wishlistRecyclerview.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}