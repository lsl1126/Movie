package com.hd.movie.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hd.movie.base.VodData
import com.hd.movie.databinding.ListItemVodBinding

class VodAdapter(private val context: Context) :
    PagingDataAdapter<VodData, VodAdapter.MyViewHolder>(VodComparator) {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.apply {
                textVod.text = it.vod_name
                Glide.with(context)
                    .load(it.vod_pic)
                    .placeholder(ColorDrawable(Color.LTGRAY))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                    .into(imageVod)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemVodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class MyViewHolder(binding: ListItemVodBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageVod = binding.imageVod
        val textVod = binding.textVod
    }

    object VodComparator : DiffUtil.ItemCallback<VodData>() {
        override fun areItemsTheSame(oldItem: VodData, newItem: VodData): Boolean {
            return oldItem.vod_id == newItem.vod_id
        }

        override fun areContentsTheSame(oldItem: VodData, newItem: VodData): Boolean {
            return oldItem == newItem
        }

    }

}