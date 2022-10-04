package com.example.taskmanager.accounts.settings;

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import com.example.taskmanager.accounts.entities.UserSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Implementation of [AppSettings] based on [AccountManager]. Main implementation.
 */

class AccountManagerAppSettings @Inject constructor(
    @ApplicationContext appContext: Context,
    private val accountManager: AccountManager
): AppSettings {

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
        val bundle = Bundle()
            bundle.putString("accessToken", accountSettings.accessToken)
            bundle.putString("avatarUrl",accountSettings.avatarUrl)
            bundle.putString("email",accountSettings.email)
            bundle.putString("id",accountSettings.id)
            bundle.putString("refreshToken",accountSettings.refreshToken)
            bundle.putString("tokenType",accountSettings.tokenType)
            bundle.putString("username",accountSettings.username)
            bundle.putString("expiresIn",accountSettings.expiresIn.toString())
        accountManager.addAccountExplicitly(Account(accountSettings.email, ACCOUNT_TYPE),null ,null)
    }

    companion object {
        private const val PREF_CURRENT_ACCOUNT_TOKEN = "currentToken"
        private const val ACCOUNT_TYPE = "com.example.taskmanager"
    }
}