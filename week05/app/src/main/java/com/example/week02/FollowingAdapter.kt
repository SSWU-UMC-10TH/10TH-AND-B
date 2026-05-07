package com.example.week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week02.databinding.ItemFollowingBinding

class FollowingAdapter(private val userList: List<ReqResUser>) : RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFollowingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(userList[position].avatar)
            .into(holder.binding.itemFollowingIv)
    }
}