package com.example.umc_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_week2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeProductList = listOf(
            ProductData(
                R.drawable.main_shoes1,
                "Air Jordan XXXVI",
                "",
                "",
                "US$185"
            ),
            ProductData(
                R.drawable.main_shoes2,
                "Nike Air Force 1 '07",
                "",
                "",
                "US$115"
            )
        )

        val adapter = HomeProductAdapter(homeProductList)

        binding.rvHomeProducts.adapter = adapter
        binding.rvHomeProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}