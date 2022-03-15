package com.hd.movie.network

data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val msg: String
)