package com.example.curativepis.di

import android.content.Context
import android.content.SharedPreferences
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.feature_auth.data.AuthApi
import com.example.curativepis.feature_auth.data.repository.AuthRepositoryImpl
import com.example.curativepis.feature_auth.domian.repository.AuthRepository
import com.example.curativepis.feature_auth.domian.use_case.*
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Auth {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideGsonAdapter():Gson= Gson()


    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideScannerRepository(firebaseAuth: FirebaseAuth,api: AuthApi): AuthRepository =
        AuthRepositoryImpl(firebaseAuth = firebaseAuth,api=api)

    @Provides
    @Singleton
    fun provideScannerCurativePisApi(client: OkHttpClient): AuthApi =
        Retrofit.Builder()
            .baseUrl(Constants.CURATIVE_API_BASES_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)




    @Provides
    @Singleton
    fun provideLoginUseCase(
        firebaseAuth: FirebaseAuth,
        sharedPreferences: SharedPreferences,
        repository: AuthRepository
    ): AuthUseCase =
        AuthUseCase(
            validUsernameUseCase = VaidatUsernameUseCase(),
            validEmailUseCase = VaidteEmailUseCase(),
            validPasswordUseCase = VaidatPasswordUseCase(),
            validConfirmPasswordUseCase = VaidatConfirmPasswordUseCase(),
            validPhoneUseCase = VaidtePhoneUseCase(),
            validOTPCodeUseCase = VaidteOTPCodeUseCase(),
            verificationOtpUseCase = VerificationOtpUseCase(
                firebaseAuth = firebaseAuth,
                sharedPreferences = sharedPreferences,
            ),
            sendOtpMessageUseCase = SendOtpMessageUseCase(
                firebaseAuth = firebaseAuth,
                sharedPreferences = sharedPreferences,
            ),
            pushNewUserUseCase = PushNewUserUseCase(repository = repository),
            getFirebaseCurrentUser = GetFirebaseCurrentUser(firebaseAuth = firebaseAuth)
        )
}