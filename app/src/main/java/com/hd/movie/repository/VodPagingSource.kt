package com.hd.movie.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hd.movie.bean.VodData
import com.hd.movie.network.RetrofitUtils

class VodPagingSource(private val typeId: Int = 0, private val wd: String = "") :
    PagingSource<Int, VodData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VodData> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = RetrofitUtils.getApiUrl().getVodList(nextPageNumber, typeId, wd)
            val nextKey = nextPageNumber + 1

            val list = response.data.list

            if (list.isNotEmpty()) {
                LoadResult.Page(response.data.list, null, nextKey)
            } else {
                LoadResult.Error(Throwable("data_empty"))
            }
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, VodData>): Int? = null
}