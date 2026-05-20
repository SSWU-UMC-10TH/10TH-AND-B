package com.example.week05.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week05.R
import com.example.week05.data.model.Follow
import com.example.week05.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProfileBinding.bind(view)


        val dummyList = List(10) { Follow() }


        binding.followRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        binding.followRecyclerView.adapter = FollowAdapter(dummyList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}