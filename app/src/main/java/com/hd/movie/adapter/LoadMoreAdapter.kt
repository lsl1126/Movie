package com.hd.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hd.movie.databinding.LayoutFooterLoadStateBinding

class LoadMoreAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadMoreAdapter.MyViewHolder>() {

    class MyViewHolder(binding: LayoutFooterLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val progressBar = binding.progressBar
        val tvText = binding.tvText
    }

    override fun onBindViewHolder(holder: MyViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        when (loadState) {
            is LoadState.Error -> {
                if (loadState.error.message.equals("data_empty")) {
                    holder.tvText.text = "没有更多数据了"
                } else {
                    holder.tvText.text = "加载失败，点击重试"
                }
            }
            is LoadState.Loading -> {
                holder.tvText.text = "加载中..."
            }
            is LoadState.NotLoading -> {}
        }
        holder.tvText.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MyViewHolder {
        return MyViewHolder(
            LayoutFooterLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}