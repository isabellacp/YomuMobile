package com.isabellacp.dev.app.yomu.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.isabellacp.dev.app.yomu.api.ApiService
import com.isabellacp.dev.app.yomu.models.Data

class MangaPagingSource(private val apiService: ApiService) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getManga(8, currentPage)
            val data = response.body()?.data ?: emptyList()
            val responseData = mutableListOf<Data>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else 1,
                nextKey = currentPage.plus(8)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}