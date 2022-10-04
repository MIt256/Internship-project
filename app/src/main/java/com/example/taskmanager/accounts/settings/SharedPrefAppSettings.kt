package com.example.taskmanager.accounts.settings

import android.content.Context
import com.example.taskmanager.accounts.entities.UserSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Implementation of [AppSettings] based on [SharedPreferences].
 */

class SharedPrefAppSettings @Inject constructor(
    @ApplicationContext appContext: Context
): AppSettings {

    private val sharedPref = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getCurrentToken(): String? {
//       return  sharedPref.getString(PREF_CURRENT_ACCOUNT_TOKEN, null)
        return null
    }

    override fun setCurrentToken(token: String?) {
//        val editor = sharedPref.edit()
//        if (token == null)
//            editor.remove(PREF_CURRENT_ACCOUNT_TOKEN)
//        else
//            editor.putString(PREF_CURRENT_ACCOUNT_TOKEN, token)
//        editor.apply()
    }

    override fun createAccount(accountSettings: UserSettings) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val PREF_CURRENT_ACCOUNT_TOKEN = "currentToken"
    }
}