package com.hd.movie.repository

import com.hd.movie.base.Vod
import com.hd.movie.base.VodType
import com.hd.movie.network.RetrofitUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class HomeRepository {

    suspend fun getVodTypes(): VodType {
        return RetrofitUtils.getApiUrl().getVodTypes().data
    }

    suspend fun getVod(page: Int, type: Int): Vod {
        return RetrofitUtils.getApiUrl().getVodList(page, type).data
    }

}