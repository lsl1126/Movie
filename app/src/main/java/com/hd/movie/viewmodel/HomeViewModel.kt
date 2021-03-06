package com.hd.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hd.movie.bean.VodData
import com.hd.movie.bean.VodType
import com.hd.movie.repository.HomeRepository
import com.hd.movie.repository.VodPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val vodTypeFlow = MutableStateFlow<VodType?>(null)

    private val repository by lazy {
        HomeRepository()
    }

    fun getVodTypes() {
        viewModelScope.launch {
            vodTypeFlow.value = repository.getVodTypes()
        }
    }

    fun getVod(typeId: Int): Flow<PagingData<VodData>> {
        return Pager(PagingConfig(15)) {
            VodPagingSource(typeId)
        }.flow.cachedIn(viewModelScope)
    }

}