package com.hd.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hd.movie.bean.VodData
import com.hd.movie.databinding.ListItemSearchVodBinding
import com.hd.movie.databinding.ListItemVodBinding

class SearchAdapter(private val context: Context) :
    PagingDataAdapter<VodData, SearchAdapter.MyViewHolder>(VodComparator) {


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.apply {
                tvName.text = item.vod_name
                tvArea.text = "${item.type_name} ${item.vod_area}"
                tvActor.text = "演员：${item.vod_actor}"
                tvRemarks.text = item.vod_remarks
                tvBlurb.text = "简介：${item.vod_blurb}"
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
            ListItemSearchVodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class MyViewHolder(binding: ListItemSearchVodBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageVod = binding.imageVod
        val tvName = binding.tvName
        val tvArea = binding.tvTypeArea
        val tvActor = binding.tvActor
        val tvRemarks = binding.tvRemarks
        val tvBlurb = binding.tvBlurb
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