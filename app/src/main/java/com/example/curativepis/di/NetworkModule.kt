package com.example.curativepis.di

import com.example.curativepis.core.util.network.Constants
import com.example.curativepis.feature_drugs.data.remote.CurativePisApi
import com.example.curativepis.feature_news.data.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClint(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.CURATIVE_API_BASES_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCurativePisApi(retrofit: Retrofit): CurativePisApi =
        retrofit.create(CurativePisApi::class.java)


    @Provides
    @Singleton
    fun provideNewApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)


}