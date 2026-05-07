package com.example.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager // 🚨 GridLayoutManager 사용을 위해 import 추가 필수!
import com.example.week02.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val dummyList = arrayListOf(
            Product(R.drawable.ic_launcher_background, "Air Jordan 1", "$150"),
            Product(R.drawable.ic_launcher_background, "Nike Dunk Low", "$110"),
            Product(R.drawable.ic_launcher_background, "Air Force 1 '07", "$100"),
            Product(R.drawable.ic_launcher_background, "Zoom Pegasus 38", "$130"),
            Product(R.drawable.ic_launcher_background, "Air Jordan 1", "$150"),
            Product(R.drawable.ic_launcher_background, "Nike Dunk Low", "$110"),
            Product(R.drawable.ic_launcher_background, "Air Force 1 '07", "$100"),
            Product(R.drawable.ic_launcher_background, "Zoom Pegasus 38", "$130")
        )

        val productAdapter = ProductAdapter(dummyList)

        binding.homeProductRv.adapter = productAdapter

        binding.homeProductRv.layoutManager = GridLayoutManager(context, 2)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}