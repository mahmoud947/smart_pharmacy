package com.example.curativepis.feature_auth.domian.use_case

import android.content.SharedPreferences
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.feature_auth.domian.model.CurrentUserResponse
import com.google.gson.Gson

class GetCurrenUserFromLocalUseCase(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
) {
    operator fun invoke():CurrentUserResponse {
        val defaultUserValue = CurrentUserResponse(
            "",
            "",
            "",
            "",
            "",
            true,
            "",
            "",
            "",
            "",
            ""
        )
        val defaultUserValueAsJson = gson.toJson(defaultUserValue)
        val userInJson = sharedPreferences.getString(Constants.CURRENT_USER_KEY, defaultUserValueAsJson)
       return gson.fromJson(userInJson,CurrentUserResponse::class.java)
    }
}