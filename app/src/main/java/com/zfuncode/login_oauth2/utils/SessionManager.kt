package com.zfuncode.login_oauth2.utils

import android.content.Context
import android.content.SharedPreferences
import com.zfuncode.login_oauth2.R

class SessionManager (context : Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val ACCESS_TOKEN = "access_token"
    }

//    SIMPAN TOKEN
    fun saveAccessToken(token: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, token)
            .apply()
    }

//    GET TOKEN
    fun fetchAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

//    DELETE TOKEN
    fun deleteAccessToken() {
        val editor = prefs.edit()
        editor.clear()
            .apply()
    }

}