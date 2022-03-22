package com.hd.movie.repository

import com.hd.movie.bean.Vod
import com.hd.movie.bean.VodType
import com.hd.movie.network.RetrofitUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class HomeRepository {

    suspend fun getVodTypes(): VodType {
        return RetrofitUtils.getApiUrl().getVodTypes().data
    }
}