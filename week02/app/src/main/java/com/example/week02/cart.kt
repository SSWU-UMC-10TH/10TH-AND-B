package com.example.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.week02.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 피그마 요구사항: 주문하기 버튼 클릭 시 '구매하기' 탭으로 이동
        binding.btnOrder.setOnClickListener {
            findNavController().navigate(R.id.menu_shop)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}