package com.example.curativepis.di

import com.example.curativepis.core.util.network.Constants
import com.example.curativepis.feature_drugs.data.remote.DrugsCurativePisApi
import com.example.curativepis.feature_news.data.remote.NewsApi
import com.example.curativepis.feature_scanner.data.remote.ScannerCurativePisApi
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
    fun provideCurativePisApi(retrofit: Retrofit): DrugsCurativePisApi =
        retrofit.create(DrugsCurativePisApi::class.java)


    @Provides
    @Singleton
    fun provideNewApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideScannerCurativePisApi(client: OkHttpClient): ScannerCurativePisApi =
        Retrofit.Builder()
            .baseUrl("http://192.168.1.3:3000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ScannerCurativePisApi::class.java)



}