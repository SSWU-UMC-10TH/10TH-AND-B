package com.example.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week02.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)

        val wishlistData = arrayListOf(
            Product(R.drawable.ic_launcher_background, "조던 1 레트로 하이", "219,000원"),
            Product(R.drawable.ic_launcher_background, "나이키 덩크 로우", "129,000원"),
            Product(R.drawable.ic_launcher_background, "에어포스 1 '07", "139,000원"),
            Product(R.drawable.ic_launcher_background, "줌 보메로 5", "189,000원"),
            Product(R.drawable.ic_launcher_background, "에어 맥스 97", "199,000원"),
            Product(R.drawable.ic_launcher_background, "나이키 코르테즈", "99,000원")
        )

        val wishAdapter = ProductAdapter(wishlistData)
        binding.wishlistProductRv.adapter = wishAdapter

        binding.wishlistProductRv.layoutManager = GridLayoutManager(context, 2)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}