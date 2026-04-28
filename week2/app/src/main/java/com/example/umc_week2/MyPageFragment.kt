package com.example.umc_week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.umc_week2.databinding.FragmentMyPageBinding
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var followingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMyPageData()
    }

    private fun setupRecyclerView() {
        followingAdapter = FollowingAdapter()

        binding.rvFollowing.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun loadMyPageData() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val userResponse = ApiClient.reqresService.getUser(1)
                val user = userResponse.data

                binding.tvProfileName.text = "${user.firstName}${user.lastName}"

                Glide.with(this@MyPageFragment)
                    .load(user.avatar)
                    .circleCrop()
                    .into(binding.imgProfile)

                val listResponse = ApiClient.reqresService.getUsers(1)
                followingAdapter.submitList(listResponse.data.take(3))

                binding.tvFollowingTitle.text = "팔로잉 (${listResponse.data.take(3).size})"

            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "데이터를 불러오지 못했습니다: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}