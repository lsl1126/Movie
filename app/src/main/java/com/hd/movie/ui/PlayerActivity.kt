package com.hd.movie.ui

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.gyf.immersionbar.ktx.immersionBar
import com.hd.movie.R
import com.hd.movie.databinding.ActivityHomeBinding
import com.hd.movie.databinding.ActivityPlayerBinding
import com.hd.movie.viewmodel.HomeViewModel
import com.hd.movie.viewmodel.PlayerViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    private val viewModel by viewModels<PlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collect()
        initView()
        initData()
    }

    private fun initView() {
        immersionBar {
            statusBarColor(R.color.black)
        }
    }

    private fun initData() {
        viewModel.getVodDetail(intent.getIntExtra("vodId", 0))
    }


    private fun collect() {
        lifecycleScope.launch {
            viewModel.vodDetailFlow.filterNotNull().collect {

            }
        }
    }
}