package com.hd.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hd.movie.bean.VodData
import com.hd.movie.repository.VodPagingSource
import kotlinx.coroutines.flow.Flow

class SearchViewModel : ViewModel() {

    fun getVod(wd: String): Flow<PagingData<VodData>> {
        return Pager(PagingConfig(15)) {
            VodPagingSource(wd = wd)
        }.flow.cachedIn(viewModelScope)
    }

}