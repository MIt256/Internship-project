package com.example.taskmanager.accounts.settings

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.taskmanager.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AccountAuth(
    private val curContext: Context
)  : AbstractAccountAuthenticator(curContext) {

    override fun editProperties(p0: AccountAuthenticatorResponse?, p1: String?): Bundle {
        TODO("Not yet implemented")
    }

    override fun addAccount(
        p0: AccountAuthenticatorResponse?,
        p1: String?,
        p2: String?,
        p3: Array<out String>?,
        p4: Bundle?
    ): Bundle {
        val intent = Intent(curContext, MainActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, p0)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, p1)
        intent.putExtra(AccountManager.KEY_AUTH_TOKEN_LABEL, p3)
        intent.putExtra("EXTRA_FROM_SETTINGS_FLAG",true)

        val result = Bundle()
        result.putParcelable(AccountManager.KEY_INTENT, intent)
        return result
    }

    override fun confirmCredentials(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: Bundle?
    ): Bundle {
        TODO("Not yet implemented")
    }

    override fun getAuthToken(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: String?,
        p3: Bundle?
    ): Bundle {
        TODO("Not yet implemented")
    }

    override fun getAuthTokenLabel(p0: String?): String {
        TODO("Not yet implemented")
    }

    override fun updateCredentials(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: String?,
        p3: Bundle?
    ): Bundle {
        TODO("Not yet implemented")
    }

    override fun hasFeatures(
        p0: AccountAuthenticatorResponse?,
        p1: Account?,
        p2: Array<out String>?
    ): Bundle {
        TODO("Not yet implemented")
    }

}