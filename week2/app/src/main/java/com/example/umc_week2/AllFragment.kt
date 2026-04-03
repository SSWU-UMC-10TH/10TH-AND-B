package com.example.umc_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_week2.databinding.FragmentAllBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AllFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    private var productList = mutableListOf<ProductData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBuyProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        val defaultProducts = listOf(
            ProductData(
                R.drawable.socks1,
                "Nike Everyday Plus Cushioned",
                "Training Ankle Socks (6 Pairs)",
                "5 Colours",
                "US$10",
                false,
                true,
                true
            ),
            ProductData(
                R.drawable.socks2,
                "Nike Elite Crew",
                "Basketball Socks",
                "7 Colours",
                "US$16",
                false,
                false,
                true
            ),
            ProductData(
                R.drawable.shoes1,
                "Nike Air Force 1 '07",
                "Women's Shoes",
                "5 Colours",
                "US$115",
                true,
                false,
                true
            ),
            ProductData(
                R.drawable.shoes2,
                "Jordan E Nike Air Force 1 '07ssentials",
                "Men's Shoes",
                "2 Colours",
                "US$115",
                true,
                false,
                true
            )
        )

        lifecycleScope.launch {
            val savedProducts = ProductDataStore.getBuyProducts(requireContext()).first()

            productList = if (savedProducts.isEmpty()) {
                ProductDataStore.saveBuyProducts(requireContext(), defaultProducts)
                defaultProducts.toMutableList()
            } else {
                savedProducts.toMutableList()
            }

            setupAdapter()
        }
    }

    private fun setupAdapter() {
        val adapter = ProductAdapter(productList) { position ->
            productList[position].isLiked = !productList[position].isLiked

            lifecycleScope.launch {
                ProductDataStore.saveBuyProducts(requireContext(), productList)
                setupAdapter()
            }
        }

        binding.rvBuyProducts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}