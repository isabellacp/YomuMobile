package com.isabellacp.dev.app.yomu.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.isabellacp.dev.app.yomu.adapter.SearchAdapter
import com.isabellacp.dev.app.yomu.api.ApiService
import com.isabellacp.dev.app.yomu.databinding.ActivitySearchBinding
import com.isabellacp.dev.app.yomu.di.AppModule
import com.isabellacp.dev.app.yomu.models.Data
import com.isabellacp.dev.app.yomu.util.Constants
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var animeList: ArrayList<Data>

    private var timer = Timer()
    private var DELAY: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animeList = ArrayList()
        animeList.clear()
        val currentPage = intent.getIntExtra("pos", 0)

        setupRecyelrview()

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        lifecycleScope.launch {
                            animeList = searchItem(binding.searchView.text.toString(), currentPage)
                            setupRecyelrview()
                        }
                    }
                }, DELAY)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer.cancel() //Terminates this timer,discarding any currently scheduled tasks.
                timer.purge() //Removes all cancelled tasks from this timer's task queue.
            }
        })
    }

    private fun setupRecyelrview() {
        searchAdapter = SearchAdapter(animeList)
        if (animeList.size > 0) {
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = searchAdapter
            }
        }
    }

    suspend fun searchItem(query: String, page: Int): ArrayList<Data> {
        if (page == 0) {
            val apiService: ApiService = AppModule.provideRetrofitInstance(Constants.BASE_URL)
            val response = apiService.searchAnime(query)
            val data = response.body()?.data ?: emptyList()
            val responseData = arrayListOf<Data>()
            responseData.addAll(data)
            return responseData
        }
        val apiService: ApiService = AppModule.provideRetrofitInstance(Constants.BASE_URL)
        val response = apiService.searchManga(query)
        val data = response.body()?.data ?: emptyList()
        val responseData = arrayListOf<Data>()
        responseData.addAll(data)
        return responseData
    }
}