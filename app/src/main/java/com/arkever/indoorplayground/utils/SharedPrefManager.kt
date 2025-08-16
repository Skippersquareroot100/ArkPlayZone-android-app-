package com.arkever.indoorplayground.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context:Context) {

    private  val PREF_NAME = "jwt_token_prefs"
    private  val KEY_TOKEN = "token"

    private  val sharePref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)


    fun saveToken(token :String)
    {
        val editor = sharePref.edit()
        editor.putString(KEY_TOKEN, "Bearer $token")
        editor.apply()
    }

    fun getToken():String?
    {
        return  sharePref.getString(KEY_TOKEN, null)
    }
    fun clearToken()
    {
        val editor = sharePref.edit()
        editor.remove(KEY_TOKEN)
        editor.apply()
    }



}