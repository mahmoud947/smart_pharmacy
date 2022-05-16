package com.example.curativepis.feature_auth.domian.use_case

import android.content.SharedPreferences
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.feature_auth.domian.model.CurrentUserResponse
import com.google.gson.Gson

class RemoveCurrenFromLocalUserUseCase(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {
    operator fun invoke(){
        sharedPreferences.edit().remove(Constants.CURRENT_USER_KEY).apply()
    }
}