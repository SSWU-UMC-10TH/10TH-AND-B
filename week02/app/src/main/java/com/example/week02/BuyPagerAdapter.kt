package com.example.week02 // 본인의 패키지명과 같은지 꼭 확인해 주세요!

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BuyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> TFragment()
            2 -> ShoesFragment()
            else -> AllFragment()
        }
    }
}