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
import com.hd.movie.bean.VodData
import com.hd.movie.databinding.ListItemVodBinding

class VodAdapter(private val context: Context) :
    PagingDataAdapter<VodData, VodAdapter.MyViewHolder>(VodComparator) {


    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.apply {
                tvVod.text = it.vod_name
                tvRemarks.text = it.vod_remarks
                tvScore.text = it.vod_score
                Glide.with(context)
                    .load(it.vod_pic)
                    .placeholder(ColorDrawable(Color.LTGRAY))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                    .into(imageVod)
            }
            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(item)
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
        val tvVod = binding.tvVod
        val tvRemarks = binding.tvRemarks
        val tvScore = binding.tvScore
    }

    object VodComparator : DiffUtil.ItemCallback<VodData>() {
        override fun areItemsTheSame(oldItem: VodData, newItem: VodData): Boolean {
            return oldItem.vod_id == newItem.vod_id
        }

        override fun areContentsTheSame(oldItem: VodData, newItem: VodData): Boolean {
            return oldItem == newItem
        }

    }

    fun interface OnItemClickListener {
        fun onItemClick(vodData: VodData)
    }

}