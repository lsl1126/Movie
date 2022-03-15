package com.hd.movie.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hd.movie.base.VodData
import com.hd.movie.network.RetrofitUtils

class VodPagingSource(private val typeId: Int) : PagingSource<Int, VodData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VodData> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = RetrofitUtils.getApiUrl().getVodList(nextPageNumber, typeId)
            val nextKey = nextPageNumber + 1
            LoadResult.Page(response.data.list, null, nextKey)
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, VodData>): Int? = null
}