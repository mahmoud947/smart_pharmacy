package com.example.curativepis.di

import com.example.curativepis.core.commn.Constants
import com.example.curativepis.feature_cart.data.remote.CartApi
import com.example.curativepis.feature_cart.data.repository.CartRepositoryImpl
import com.example.curativepis.feature_cart.domian.repository.CartRepository
import com.example.curativepis.feature_cart.domian.use_case.*
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Provides
    @Singleton
    fun provideCartApi(client:OkHttpClient): CartApi {
        return Retrofit.Builder()
            .baseUrl(Constants.CURATIVE_API_BASES_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartApi::class.java)
    }



    @Provides
    @Singleton
    fun provideCartRepository(api: CartApi): CartRepository =
        CartRepositoryImpl(api=api)

    @Provides
    @Singleton
    fun provideCartUseCase(repository: CartRepository,firebaseAuth: FirebaseAuth):CartUseCase=
        CartUseCase(
            getCartUseCase = GetCartUseCase(repository = repository),
            getUserToken = GetUserToken(firebaseAuth = firebaseAuth),
            deleteCartItemUseCase = DeleteCartItemUseCase(repository = repository),
            purchaseUserCarttUseCase = PurchaseUserCarttUseCase(repository = repository),
            getCartHistorytUseCase = GetCarHistorytUseCase(repository = repository)
        )
}