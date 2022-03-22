package com.hd.movie.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hd.movie.bean.VodType
import com.hd.movie.databinding.ActivityHomeBinding
import com.hd.movie.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collect()
        initData()
    }

    private fun collect() {
        lifecycleScope.launch {
            viewModel.vodTypeFlow.filterNotNull().collect {
                initView(it)
            }
        }
    }

    private fun initData() {
        viewModel.getVodTypes()
    }

    private fun initView(type: VodType) {
        binding.apply {
            viewPager.adapter = HomeAdapter(this@HomeActivity, type)
            viewPager.offscreenPageLimit = type.list.size
            TabLayoutMediator(
                tabLayout,
                viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = type.list[position].type_name
            }.attach()
        }
        binding.search.setOnClickListener {
            startActivity(
                Intent(this, SearchActivity::class.java)
            )
        }
    }

    class HomeAdapter(fragmentActivity: FragmentActivity, private val type: VodType) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = type.list.size

        override fun createFragment(position: Int): Fragment {
            val fragment = HomeFragment()
            fragment.arguments = Bundle().apply {
                putInt("typeId", type.list[position].type_id)
            }
            return fragment
        }
    }


}