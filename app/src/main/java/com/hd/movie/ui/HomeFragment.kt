package com.hd.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hd.movie.adapter.FooterLoadStateAdapter
import com.hd.movie.adapter.VodAdapter
import com.hd.movie.databinding.FragmentHomeBinding
import com.hd.movie.viewmodel.HomeViewModel
import com.hd.movie.widgets.SpacesItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var vodAdapter: VodAdapter

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initView() {
        context?.let {
            vodAdapter = VodAdapter(it)
            binding.apply {
                vodList.adapter =
                    vodAdapter.withLoadStateFooter(FooterLoadStateAdapter { vodAdapter.retry() })

                val gridLayoutManager = GridLayoutManager(context, 3)
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == vodAdapter.itemCount) 3 else 1
                    }
                }
                vodList.layoutManager = gridLayoutManager
                vodList.addItemDecoration(SpacesItemDecoration(20))
                vodList.setPadding(10, 20, 10, 0)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        arguments?.takeIf {
            it.containsKey("typeId")
        }?.apply {
            initData(getInt("typeId"))
        }
    }

    private fun initData(typeId: Int) {
        lifecycleScope.launch {
            viewModel.getVod(typeId).collectLatest {
                vodAdapter.submitData(it)
            }
        }
    }
}