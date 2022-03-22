package com.hd.movie.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.hd.movie.adapter.LoadMoreAdapter
import com.hd.movie.adapter.VodAdapter
import com.hd.movie.bean.VodData
import com.hd.movie.databinding.FragmentHomeBinding
import com.hd.movie.viewmodel.HomeViewModel
import com.hd.movie.widgets.SpacesItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var vodAdapter: VodAdapter

    private var typeId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf {
            it.containsKey("typeId")
        }?.apply {
            typeId = getInt("typeId")
        }
        initView()
        initData()
    }

    private fun initView() {
        binding.vodList.apply {
            vodAdapter = VodAdapter(context)
            adapter = vodAdapter.withLoadStateFooter(LoadMoreAdapter { vodAdapter.retry() })
            val gridLayoutManager = GridLayoutManager(context, 3)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == vodAdapter.itemCount) 3 else 1
                }
            }
            layoutManager = gridLayoutManager
            addItemDecoration(SpacesItemDecoration(20))
            setPadding(10, 20, 10, 0)
        }
        binding.refreshLayout.apply {
            vodAdapter.addLoadStateListener {
                isRefreshing = it.refresh is LoadState.Loading
            }
            setOnRefreshListener {
                vodAdapter.refresh()
            }
        }
        vodAdapter.setOnItemClickListener {
            startActivity(
                Intent(this.activity, PlayerActivity::class.java).putExtra(
                    "vodId",
                    it.vod_id
                )
            )
        }
    }

    private fun initData() {
        lifecycleScope.launch {
            viewModel.getVod(typeId).filterNotNull().collectLatest {
                vodAdapter.submitData(it)
            }
        }
    }
}