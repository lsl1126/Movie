package com.hd.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hd.movie.bean.VodData
import com.hd.movie.bean.VodDetail
import com.hd.movie.bean.VodType
import com.hd.movie.repository.HomeRepository
import com.hd.movie.repository.PlayerRepository
import com.hd.movie.repository.VodPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {

    val vodDetailFlow = MutableStateFlow<VodDetail?>(null)

    private val repository by lazy {
        PlayerRepository()
    }

    fun getVodDetail(vodId: Int) {
        viewModelScope.launch {
            vodDetailFlow.value = repository.getVodDetail(vodId)
        }
    }

}