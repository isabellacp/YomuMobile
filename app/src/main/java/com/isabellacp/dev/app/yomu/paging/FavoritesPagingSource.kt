package com.example.app

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.isabellacp.dev.app.yomu.dao.FavoritesDAO
import com.isabellacp.dev.app.yomu.models.Data

class FavoritesPagingSource(private val seriesDao: FavoritesDAO) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val currentPage = params.key ?: 0
            val data = seriesDao.getFavouriteSeries(currentPage * 15, 15)
            val responseData = mutableListOf<Data>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 0) null else currentPage - 15,
                nextKey = if (data.isEmpty()) null else currentPage + 15
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}