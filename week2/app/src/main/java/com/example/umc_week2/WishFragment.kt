package com.example.umc_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_week2.databinding.FragmentWishBinding

class WishFragment : Fragment() {

    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishProductList = listOf(
            ProductData(
                R.drawable.socks1,
                "Air Jordan 1 Mid",
                "",
                "",
                "US$125",
                false,
                false,
                false
            ),
            ProductData(
                R.drawable.shoes3,
                "Nike Everyday Plus Cushioned",
                "Training Ankle Socks (6 Pairs)",
                "5 Colours",
                "US$10",
                false,
                false,
                false
            )
        )

        val adapter = ProductAdapter(wishProductList)

        binding.rvWishProducts.adapter = adapter
        binding.rvWishProducts.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}