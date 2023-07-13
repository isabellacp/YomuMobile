package com.isabellacp.dev.app.yomu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.app.FavoritesPagingSource
import com.isabellacp.dev.app.yomu.api.ApiService
import com.isabellacp.dev.app.yomu.dao.FavoritesDAO
import com.isabellacp.dev.app.yomu.models.Data
import com.isabellacp.dev.app.yomu.paging.AnimePagingSource
import com.isabellacp.dev.app.yomu.paging.MangaPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel
@Inject constructor(
    private val apiService: ApiService,
    private val favoritesDAO: FavoritesDAO
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        AnimePagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

    val favorites = Pager(PagingConfig(pageSize = 1)) {

        FavoritesPagingSource(favoritesDAO)
    }.flow.cachedIn(viewModelScope)

    val mangaData = Pager(PagingConfig(pageSize = 1)) {
        MangaPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)


    suspend fun addToFavorites(data: Data) {
        data.is_favorited = true;
        favoritesDAO.insert(data)
    }
}