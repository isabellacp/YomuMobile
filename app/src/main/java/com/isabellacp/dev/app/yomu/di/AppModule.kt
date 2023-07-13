package com.isabellacp.dev.app.yomu.di

import android.content.Context
import androidx.room.Room
import com.isabellacp.dev.app.yomu.api.ApiService
import com.isabellacp.dev.app.yomu.dao.FavoritesDAO
import com.isabellacp.dev.app.yomu.db.YomuDatabase
import com.isabellacp.dev.app.yomu.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): YomuDatabase {
        return Room.databaseBuilder(
            appContext,
            YomuDatabase::class.java,
            "yomu_database"
        ).build()
    }

    @Provides
    fun provideFavoritesDao(yomuDatabase: YomuDatabase): FavoritesDAO {
        return yomuDatabase.favoritesDao()
    }

}