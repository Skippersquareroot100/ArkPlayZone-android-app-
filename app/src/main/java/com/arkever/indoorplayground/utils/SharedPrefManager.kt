package com.arkever.indoorplayground.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context:Context) {

    private  val PREF_NAME = "jwt_token_prefs"
    private  val KEY_TOKEN = "token"
    private  val EMAIL_KEY = "email"
    private  val EXPIRES_AT_KEY = "expires_at"


    private  val sharePref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)


    fun saveUser(email:String ,token :String, expiresInSeconds:Long)
    {
        val expiresAT = System.currentTimeMillis() + expiresInSeconds*1000
        sharePref.edit().putString(EMAIL_KEY,email)
            .putString(KEY_TOKEN,"Bearer "+token)
            .putLong(EXPIRES_AT_KEY,expiresAT)
            .apply()
    }

    fun getToken():String?
    {
        return  sharePref.getString(KEY_TOKEN, null)
    }
    fun getEmail():String?
    {
        return sharePref.getString(EMAIL_KEY, null)
    }
    fun isTokenExpired() :Boolean
    {
        return  System.currentTimeMillis() >= sharePref.getLong(EXPIRES_AT_KEY, 0)
    }



    fun clearToken()
    {
        sharePref.edit().clear().apply()
    }



}