package com.example.umc_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_week2.databinding.FragmentBuyBinding

class BuyFragment : Fragment() {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            ProductData(
                R.drawable.socks1,
                "Nike Everyday Plus Cushioned",
                "Training Ankle Socks (6 Pairs)",
                "5 Colours",
                "US$10",
                false,
                true
            ),
            ProductData(
                R.drawable.socks2,
                "Nike Elite Crew",
                "Basketball Socks",
                "7 Colours",
                "US$16",
                false,
                false
            ),
            ProductData(
                R.drawable.shoes1,
                "Nike Air Force 1 '07",
                "Women's Shoes",
                "5 Colours",
                "US$115",
                true,
                false
            ),
            ProductData(
                R.drawable.shoes2,
                "Jordan E Nike Air Force 1 '07ssentials",
                "Men's Shoes",
                "2 Colours",
                "US$115",
                true,
                false
            )
        )

        val adapter = ProductAdapter(productList)
        binding.rvBuyProducts.adapter = adapter
        binding.rvBuyProducts.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}