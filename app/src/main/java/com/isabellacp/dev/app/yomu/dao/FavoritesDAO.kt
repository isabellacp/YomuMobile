package com.isabellacp.dev.app.yomu.dao

import androidx.room.*
import com.isabellacp.dev.app.yomu.models.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDAO {

    @Query("SELECT * FROM Data WHERE is_favorited = 1 LIMIT :limit OFFSET :offset")
    suspend fun getFavouriteSeries(offset: Int, limit: Int): List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(series: Data)

    @Delete
    suspend fun delete(series: Data)

    @Update
    suspend fun update(series: Data)

    @Query("UPDATE Data SET is_favorited = :isFavorited WHERE id = :id")
    suspend fun updateFavorited(id: Int, isFavorited: Boolean)

    @Query("SELECT * FROM Data WHERE id = :id")
    fun observeById(id: Int): Flow<Data>

    @Query("SELECT * FROM Data WHERE is_favorited = 1")
    fun observeAllFavorited(): Flow<List<Data>>
}