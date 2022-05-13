package com.example.curativepis.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.curativepis.feature_ath.data.repository.AuthRepositoryImpl
import com.example.curativepis.feature_ath.domian.repository.AuthRepository
import com.example.curativepis.feature_ath.domian.use_case.*
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideScannerRepository(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(firebaseAuth = firebaseAuth)


    @Provides
    @Singleton
    fun provideLoginUseCase(
        firebaseAuth: FirebaseAuth,
        sharedPreferences: SharedPreferences,
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
            )
        )
}