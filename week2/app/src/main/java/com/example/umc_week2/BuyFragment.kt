package com.example.umc_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        // 처음 진입 시 전체 탭 표시
        if (savedInstanceState == null) {
            showAllTab()
        }

        binding.tabAll.setOnClickListener {
            showAllTab()
        }

        binding.tabTops.setOnClickListener {
            showTopTab()
        }

        binding.tabSale.setOnClickListener {
            showSaleTab()
        }
    }

    private fun showAllTab() {
        childFragmentManager.beginTransaction()
            .replace(R.id.buyTabContainer, AllFragment())
            .commit()

        binding.tabAll.setTextColor(resources.getColor(android.R.color.black, null))
        binding.tabTops.setTextColor(resources.getColor(android.R.color.darker_gray, null))
        binding.tabSale.setTextColor(resources.getColor(android.R.color.darker_gray, null))
    }

    private fun showTopTab() {
        childFragmentManager.beginTransaction()
            .replace(R.id.buyTabContainer, TopFragment())
            .commit()

        binding.tabAll.setTextColor(resources.getColor(android.R.color.darker_gray, null))
        binding.tabTops.setTextColor(resources.getColor(android.R.color.black, null))
        binding.tabSale.setTextColor(resources.getColor(android.R.color.darker_gray, null))
    }

    private fun showSaleTab() {
        childFragmentManager.beginTransaction()
            .replace(R.id.buyTabContainer, SaleFragment())
            .commit()

        binding.tabAll.setTextColor(resources.getColor(android.R.color.darker_gray, null))
        binding.tabTops.setTextColor(resources.getColor(android.R.color.darker_gray, null))
        binding.tabSale.setTextColor(resources.getColor(android.R.color.black, null))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}