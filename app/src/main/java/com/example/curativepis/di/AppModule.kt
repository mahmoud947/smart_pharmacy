package com.example.curativepis.di

import com.example.curativepis.core.domian.use_case.SignOutCurrentUserUseCase
import com.example.curativepis.feature_drugs.data.remote.DrugsCurativePisApi
import com.example.curativepis.feature_drugs.data.repository.DrugsRepositoryImpl
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import com.example.curativepis.feature_drugs.domian.use_case.*
import com.example.curativepis.feature_news.data.remote.NewsApi
import com.example.curativepis.feature_news.data.repository.NewsRepositoryImpl
import com.example.curativepis.feature_news.domian.repository.NewsRepository
import com.example.curativepis.feature_scanner.data.remote.ScannerCurativePisApi
import com.example.curativepis.feature_scanner.data.repository.ScannerReposetoryImpl
import com.example.curativepis.feature_scanner.domian.repository.ScannerReposetory
import com.example.curativepis.feature_scanner.domian.use_case.ScannerUseCase
import com.example.curativepis.feature_scanner.domian.use_case.UploadImageUseCase
import com.google.firebase.auth.FirebaseAuth
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
    fun provideSignOutUseCase(firebaseAuth: FirebaseAuth) =
        SignOutCurrentUserUseCase(firebaseAuth = firebaseAuth)


    @Provides
    @Singleton
    fun provideDrugsUseCase(repository: DrugsRepository,firebaseAuth: FirebaseAuth): DrugsUseCase =
        DrugsUseCase(
            getDrugsUseCase = GetDrugsUseCase(repository = repository),
            getDrugsByNameUseCase = GetDrugsByNameUseCase(repository = repository),
            getDrugByIdUseCase = GetDrugByIdUseCase(repository = repository),
            drugsGetUserTokenUseCase = DrugsGetUserTokenUseCase(firebaseAuth = firebaseAuth),
            addItemToCartUseCase = AddItemToCartUseCase(repository = repository)
        )

    @Provides
    @Singleton
    fun provideScannerUseCase(reposetory: ScannerReposetory): ScannerUseCase =
        ScannerUseCase(
            uploadImageUseCase = UploadImageUseCase(reposetory = reposetory)
        )



}