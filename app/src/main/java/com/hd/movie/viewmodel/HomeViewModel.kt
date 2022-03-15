package com.hd.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hd.movie.base.Vod
import com.hd.movie.base.VodData
import com.hd.movie.base.VodType
import com.hd.movie.repository.HomeRepository
import com.hd.movie.repository.VodPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val vodType = MutableStateFlow<VodType?>(null)

    val vod = MutableStateFlow<Vod?>(null)

    private val repository by lazy {
        HomeRepository()
    }

    fun getVodTypes() {
        viewModelScope.launch {
            vodType.value = repository.getVodTypes()
        }
    }

    fun getVod(typeId: Int): Flow<PagingData<VodData>> {
        return Pager(PagingConfig(15)) {
            VodPagingSource(typeId)
        }.flow.cachedIn(viewModelScope)
    }

}