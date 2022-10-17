package com.example.taskmanager.accounts.settings;

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.example.taskmanager.accounts.entities.UserSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Implementation of [AppSettings] based on [AccountManager]. Main implementation.
 */

class AccountManagerAppSettings @Inject constructor(
    @ApplicationContext appContext: Context,
    private val accountManager: AccountManager
) : AppSettings {

    override fun getCurrentToken(): String? {
        val account = getAccount(ACCOUNT_TYPE)
        return if (account != null)
            accountManager.getUserData(
                account,
                KEY_USER_ACCESS_TOKEN
            ) else null
    }

    override fun getCurrentId(): String? = accountManager.getUserData(
        getAccount(ACCOUNT_TYPE),
        KEY_USER_ID
    )

    override fun setCurrentToken(token: String?) {
        //        val editor = sharedPref.edit()
        //        if (token == null)
        //            editor.remove(PREF_CURRENT_ACCOUNT_TOKEN)
        //        else
        //            editor.putString(PREF_CURRENT_ACCOUNT_TOKEN, token)
        //        editor.apply()
    }

    override fun createAccount(accountSettings: UserSettings) {
        val account = Account(ACCOUNT_NAME, ACCOUNT_TYPE)
        accountManager.addAccountExplicitly(account, null, null)
        addUserInfo(account, accountSettings)
    }

    override fun checkUserToken(): Boolean {
        val account = getAccount(ACCOUNT_TYPE)
        if (account != null)
            if (System.currentTimeMillis() < accountManager.getUserData(
                    account,
                    KEY_EXPIRES_IN
                ).toLong()
            ) return true
        return false
    }

    private fun getAccount(type: String): Account? {
        return accountManager.getAccountsByType(type).firstOrNull()
    }

//    private fun clearAllAccounts(type: String): Account? {
//        return accountManager.removeAccount()
//    }

    private fun addUserInfo(account: Account, userSettings: UserSettings) {
        with(accountManager) {
            setUserData(account, KEY_USER_ACCESS_TOKEN, userSettings.accessToken)
            setUserData(account, KEY_USER_AVATAR_URL, userSettings.avatarUrl)
            setUserData(account, KEY_USER_EMAIL, userSettings.email)
            setUserData(account, KEY_USER_ID, userSettings.id)
            setUserData(account, KEY_REFRESH_TOKEN, userSettings.refreshToken)
            setUserData(account, KEY_TOKEN_TYPE, userSettings.tokenType)
            setUserData(account, KEY_USER_NAME, userSettings.username)
            setUserData(account, KEY_EXPIRES_IN, userSettings.expiresIn.toString())
        }
    }

    companion object {
        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val KEY_USER_EMAIL = "KEY_USER_EMAIL"
        private const val KEY_USER_ACCESS_TOKEN = "KEY_USER_ACCESS_TOKEN"
        private const val KEY_USER_AVATAR_URL = "KEY_USER_AVATAR_URL"
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
        private const val KEY_TOKEN_TYPE = "KEY_TOKEN_TYPE"
        private const val KEY_USER_NAME = "KEY_USER_NAME"
        private const val KEY_EXPIRES_IN = "KEY_EXPIRES_IN"

        private const val ACCOUNT_TYPE = "com.example.taskmanager"

        private const val ACCOUNT_NAME = "Task manager account"
    }
}