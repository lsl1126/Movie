package com.hd.movie.network

import com.hd.movie.base.Vod
import com.hd.movie.base.VodType
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/mogai_api.php/v1.vod/types")
    suspend fun getVodTypes(): BaseResponse<VodType>

    @GET("/mogai_api.php/v1.vod?limit=15")
    suspend fun getVodList(@Query("page") page: Int, @Query("type") type: Int): BaseResponse<Vod>

}