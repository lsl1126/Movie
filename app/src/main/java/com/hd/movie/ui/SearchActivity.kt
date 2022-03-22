package com.hd.movie.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.widget.TextView.OnEditorActionListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hd.movie.adapter.LoadMoreAdapter
import com.hd.movie.adapter.SearchAdapter
import com.hd.movie.databinding.ActivitySearchBinding
import com.hd.movie.viewmodel.SearchViewModel
import com.hd.movie.widgets.setEditTextState
import com.hd.movie.widgets.showOrHideSoftKeyboard
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch


class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        setEditTextState(binding.editText)
        binding.tvCancel.setOnClickListener {
            finish()
        }
        binding.editText.setOnEditorActionListener(OnEditorActionListener { _, _, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val text = binding.editText.text.toString()
                if (!TextUtils.isEmpty(text)) {
                    searchVod(text)
                    showOrHideSoftKeyboard(this)
                }
                return@OnEditorActionListener true
            }
            false
        })
        binding.vodList.apply {
            searchAdapter = SearchAdapter(context)
            adapter =
                searchAdapter.withLoadStateFooter(LoadMoreAdapter { searchAdapter.retry() })
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    private fun searchVod(wd: String) {
        lifecycleScope.launch {
            viewModel.getVod(wd).filterNotNull().collectLatest {
                searchAdapter.submitData(it)
            }
        }
    }

}