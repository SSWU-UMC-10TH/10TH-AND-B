package com.example.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week02.databinding.FragmentBusketBinding

class BusketFragment : Fragment() {

    private var _binding: FragmentBusketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusketBinding.inflate(inflater, container, false)

        // '주문하기' 이미지 버튼을 눌렀을 때의 동작
        binding.busketOrderBtn.setOnClickListener {
            // MainActivity에 만들어둔 탭 이동 함수를 실행합니다.
            val mainActivity = activity as MainActivity
            mainActivity.changeTabToBuy()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}