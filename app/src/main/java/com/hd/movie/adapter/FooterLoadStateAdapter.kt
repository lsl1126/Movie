package com.hd.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hd.movie.R
import com.hd.movie.databinding.LayoutFooterLoadStateBinding
import com.hd.movie.databinding.ListItemVodBinding

class FooterLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterLoadStateAdapter.MyViewHolder>() {

    class MyViewHolder(retry: () -> Unit, binding: LayoutFooterLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val progressBar: ProgressBar = binding.progressBar

//        init {
//            loadStateHint?.setOnClickListener { retry.invoke() }
//        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible= true
       // holder.progressBar.isVisible = loadState is LoadState.Loading
//        holder.progressBar.isVisible = loadState is LoadState.Loading
//        when (loadState) {
//            is LoadState.Error -> {
//                holder.loadStateHint?.text = "加载失败，点击重试"
//            }
//            is LoadState.Loading -> {
//                holder.loadStateHint?.text = "加载中..."
//            }
//            else -> {
//            }
        //    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MyViewHolder {
        return MyViewHolder(
            retry, LayoutFooterLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}