package com.example.curativepis.di

import com.example.curativepis.feature_ath.domian.use_case.*
import com.example.curativepis.feature_drugs.data.remote.DrugsCurativePisApi
import com.example.curativepis.feature_drugs.data.repository.DrugsRepositoryImpl
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import com.example.curativepis.feature_drugs.domian.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.domian.use_case.GetDrugByIdUseCase
import com.example.curativepis.feature_drugs.domian.use_case.GetDrugsByNameUseCase
import com.example.curativepis.feature_drugs.domian.use_case.GetDrugsUseCase
import com.example.curativepis.feature_news.data.remote.NewsApi
import com.example.curativepis.feature_news.data.repository.NewsRepositoryImpl
import com.example.curativepis.feature_news.domian.repository.NewsRepository
import com.example.curativepis.feature_scanner.data.remote.ScannerCurativePisApi
import com.example.curativepis.feature_scanner.data.repository.ScannerReposetoryImpl
import com.example.curativepis.feature_scanner.domian.repository.ScannerReposetory
import com.example.curativepis.feature_scanner.domian.use_case.ScannerUseCase
import com.example.curativepis.feature_scanner.domian.use_case.UploadImageUseCase
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
    fun provideNewsRepository(api: NewsApi): NewsRepository =
        NewsRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideDrugsRepository(api: DrugsCurativePisApi): DrugsRepository =
        DrugsRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideScannerRepository(api: ScannerCurativePisApi): ScannerReposetory =
        ScannerReposetoryImpl(api = api)


    @Provides
    @Singleton
    fun provideDrugsUseCase(repository: DrugsRepository): DrugsUseCase =
        DrugsUseCase(
            getDrugsUseCase = GetDrugsUseCase(repository = repository),
            getDrugsByNameUseCase = GetDrugsByNameUseCase(repository = repository),
            getDrugByIdUseCase = GetDrugByIdUseCase(repository = repository)
        )

    @Provides
    @Singleton
    fun provideScannerUseCase(reposetory: ScannerReposetory): ScannerUseCase =
        ScannerUseCase(
            uploadImageUseCase = UploadImageUseCase(reposetory = reposetory)
        )

    @Provides
    @Singleton
    fun provideLoginUseCase(): AuthUseCase =
        AuthUseCase(
            validEmailUseCase = VaidteEmailUseCase(),
            validPasswordUseCase = VaidatPasswordUseCase(),
            validConfirmPasswordUseCase = VaidatConfirmPasswordUseCase(),
            validPhoneUseCase = VaidtePhoneUseCase()
        )


}