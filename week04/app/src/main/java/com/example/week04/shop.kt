package com.example.week04

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week04.databinding.FragmentShopBinding
import kotlinx.coroutines.launch

class ShopFragment : Fragment(R.layout.fragment_shop) {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentShopBinding.bind(view)
        dataStoreManager = DataStoreManager(requireContext())

        binding.shopRecyclerview.layoutManager =
            GridLayoutManager(requireContext(), 2)

        lifecycleScope.launch {
            dataStoreManager.getProducts().collect { list ->

                val adapter = ProductAdapter(list) { updatedList ->
                    lifecycleScope.launch {
                        dataStoreManager.saveProducts(updatedList)
                    }
                }

                binding.shopRecyclerview.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}