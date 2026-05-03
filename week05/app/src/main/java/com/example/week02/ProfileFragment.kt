package com.example.week02

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week02.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import com.example.week02.RetrofitClient
import com.example.week02.FollowingAdapter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.followingRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // API 호출 바로 직전에 넣으세요
        android.widget.Toast.makeText(context, "주소: ${RetrofitClient.retrofit.baseUrl()}", android.widget.Toast.LENGTH_LONG).show()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val profileRes = RetrofitClient.profileApi.getMyProfile()
                if (profileRes.isSuccessful) {
                    val userData = profileRes.body()?.data
                    if (userData != null) {
                        val fullName = "${userData.firstName} ${userData.lastName}"
                        binding.profileNicknameTv.text = fullName

                        Glide.with(requireContext())
                            .load(userData.avatar)
                            .into(binding.profileImageIv)
                    }
                }

                val listRes = RetrofitClient.profileApi.getUserList()
                if (listRes.isSuccessful) {
                    val userList = listRes.body()?.data
                    if (userList != null) {
                        val followingAdapter = FollowingAdapter(userList)
                        binding.followingRv.adapter = followingAdapter
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "에러 발생: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}