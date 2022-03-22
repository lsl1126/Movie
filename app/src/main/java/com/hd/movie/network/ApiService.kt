package com.hd.movie.network

import com.hd.movie.bean.Vod
import com.hd.movie.bean.VodDetail
import com.hd.movie.bean.VodType
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/mogai_api.php/v1.vod/types")
    suspend fun getVodTypes(): BaseResponse<VodType>

    @GET("/mogai_api.php/v1.vod?limit=15")
    suspend fun getVodList(
        @Query("page") page: Int,
        @Query("type") type: Int,
        @Query("wd") wd: String
    ): BaseResponse<Vod>

    @GET("/mogai_api.php/v1.vod/detail")
    suspend fun getVodDetail(@Query("vod_id") vod_id: Int): BaseResponse<VodDetail>

}