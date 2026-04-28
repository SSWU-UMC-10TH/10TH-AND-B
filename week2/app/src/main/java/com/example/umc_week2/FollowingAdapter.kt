package com.example.umc_week2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.umc_week2.databinding.ItemFollowingUserBinding

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private val userList = mutableListOf<ReqresUser>()

    fun submitList(list: List<ReqresUser>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    inner class FollowingViewHolder(
        private val binding: ItemFollowingUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ReqresUser) {
            Glide.with(binding.root.context)
                .load(user.avatar)
                .centerCrop()
                .into(binding.imgFollowingProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size
}