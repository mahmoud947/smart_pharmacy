package com.example.curativepis.di

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.curativepis.core.presentation.screen.main_screen.MainViewModel
import com.example.curativepis.feature_drugs.data.remote.CurativePisApi
import com.example.curativepis.feature_drugs.data.repository.DrugsRepositoryImpl
import com.example.curativepis.feature_drugs.domain.repository.DrugsRepository
import com.example.curativepis.feature_drugs.domain.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.domain.use_case.GetDrugsUseCase
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
        NewsRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideDrugsRepository(api:CurativePisApi):DrugsRepository=
        DrugsRepositoryImpl(api = api)


    @Provides
    @Singleton
    fun provideDrugsUseCase(repository: DrugsRepository):DrugsUseCase=
        DrugsUseCase(
            getDrugsUseCase = GetDrugsUseCase(repository = repository)
        )


}