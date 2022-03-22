package com.hd.movie.repository

import com.hd.movie.bean.VodDetail
import com.hd.movie.bean.VodType
import com.hd.movie.network.RetrofitUtils

class PlayerRepository {
    suspend fun getVodDetail(vodId: Int): VodDetail {
        return RetrofitUtils.getApiUrl().getVodDetail(vodId).data
    }
}