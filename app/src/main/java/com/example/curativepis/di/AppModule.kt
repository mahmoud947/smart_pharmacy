package com.example.curativepis.di

import com.example.curativepis.feature_news.data.remote.NewsApi
import com.example.curativepis.feature_news.data.repository.NewsRepositoryImpl
import com.example.curativepis.feature_news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api:NewsApi):NewsRepository=
        NewsRepositoryImpl(api)
}