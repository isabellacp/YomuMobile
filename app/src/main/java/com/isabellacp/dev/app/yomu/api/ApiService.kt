package com.isabellacp.dev.app.yomu.api

import com.isabellacp.dev.app.yomu.models.CharacterResponse
import com.isabellacp.dev.app.yomu.models.ResponseApi
import com.isabellacp.dev.app.yomu.models.ResponseById
import com.isabellacp.dev.app.yomu.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.ANIME_ENDPOINT)
    suspend fun getAnime(
        @Query("page[limit]") pageLimit: Int,
        @Query("page[offset]") offset: Int
    ): Response<ResponseApi>

    @GET(Constants.MANGA_ENDPOINT)
    suspend fun getManga(
        @Query("page[limit]") pageLimit: Int,
        @Query("page[offset]") offset: Int
    ): Response<ResponseApi>

    @GET(Constants.CHARACTER_ENDPOINT)
    suspend fun getCharacter(
        @Query("page[limit]") pageLimit: Int,
        @Query("page[offset]") offset: Int
    ): Response<CharacterResponse>

    @GET(Constants.ANIME_ENDPOINT)
    suspend fun searchAnime(@Query("filter[text]") query: String): Response<ResponseApi>

    @GET(Constants.TRENDING_ENDPOINT)
    suspend fun trendingAnime(): Response<ResponseApi>

    @GET(Constants.ANIME_ENDPOINT)
    suspend fun findBySlug(@Query("filter[slug]") slug: String): Response<ResponseApi>

    @GET(Constants.ANIME_ENDPOINT + "/{id}")
    suspend fun findByID(@Path("id") id: Int): Response<ResponseById>

    @GET(Constants.MANGA_ENDPOINT)
    suspend fun searchManga(@Query("filter[text]") query: String): Response<ResponseApi>
}