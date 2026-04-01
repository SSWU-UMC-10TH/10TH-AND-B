package com.example.week02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.week02.databinding.FragmentBuyBinding
import com.google.android.material.tabs.TabLayoutMediator

class BuyFragment : Fragment() {

    // 뷰바인딩 세팅 (지팡이 준비)
    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)

        // 1. 뷰페이저(도화지)에 화면을 넘겨줄 어댑터 연결
        val buyAdapter = BuyPagerAdapter(this)
        binding.buyViewPager.adapter = buyAdapter

        // 2. 상단 탭 이름 목록
        val tabTitles = arrayListOf("전체", "Tops & T-Shirts", "Shoes")

        // 3. 탭바와 뷰페이저를 하나로 연결!
        TabLayoutMediator(binding.buyTabLayout, binding.buyViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        return binding.root
    }

    // 화면이 꺼질 때 지팡이도 같이 정리해주는 착한 코드
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}