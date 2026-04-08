package com.example.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week02.databinding.FragmentAllBinding

class AllFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(inflater, container, false)

        // 1. 임시(더미) 데이터 6개 만들기 (ic_launcher_background 대신 본인 이미지 사용 가능!)
        val dummyList = arrayListOf(
            Product(R.drawable.ic_launcher_background, "기본 흰 티셔츠", "15,000원"),
            Product(R.drawable.ic_launcher_background, "편안한 청바지", "32,000원"),
            Product(R.drawable.ic_launcher_background, "검은색 운동화", "55,000원"),
            Product(R.drawable.ic_launcher_background, "여름용 볼캡", "18,000원"),
            Product(R.drawable.ic_launcher_background, "체크 남방", "28,000원"),
            Product(R.drawable.ic_launcher_background, "스포츠 양말", "5,000원")
        )

        // 2. 어댑터에 데이터 넣어서 준비
        val productAdapter = ProductAdapter(dummyList)
        binding.allProductRv.adapter = productAdapter

        // 3. ★과제 조건★ 바둑판 모양으로 2칸씩 띄우기 (GridLayoutManager)
        binding.allProductRv.layoutManager = GridLayoutManager(context, 2)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}