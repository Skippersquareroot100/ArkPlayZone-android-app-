package com.arkever.indoorplayground.utils

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.CommonDataKinds.Email

class SharedPrefEmail(context: Context) {

    private  val PREF_NAME = "email_prefs"
    private val Email = "email"

    private val sharedPref: SharedPreferences=
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveFEmail(email: String)
    {
        sharedPref.edit().putString(Email,email)
            .apply()

    }

    fun getFEmail():String?
    {
        return sharedPref.getString(Email,null)
    }

    fun clearFEmail()
    {
        sharedPref.edit().clear().apply()
    }



}
