package com.example.curativepis.feature_auth.domian.use_case

import android.content.SharedPreferences
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.feature_auth.domian.model.CurrentUserResponse
import com.google.gson.Gson

class SaveCurrenUserFromLocalUseCase(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {
    operator fun invoke(currentUserResponse: CurrentUserResponse){
        val userInJson=gson.toJson(currentUserResponse)
        sharedPreferences.edit().putString(Constants.CURRENT_USER_KEY,userInJson).apply()
    }
}