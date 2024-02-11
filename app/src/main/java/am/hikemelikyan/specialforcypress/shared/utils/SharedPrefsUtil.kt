package am.hikemelikyan.specialforcypress.shared.utils

import am.hikemelikyan.specialforcypress.CypressApp
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefsUtil private constructor(context: Context) {

    private val mShared: SharedPreferences = context.getSharedPreferences("Configs", Context.MODE_PRIVATE)

    companion object {
        private var INSTANCE: SharedPrefsUtil? = null

        fun getInstance(context: Context? = null): SharedPrefsUtil {
            if (INSTANCE == null) {
                INSTANCE = SharedPrefsUtil(context ?: CypressApp.getInstance()!!)
            }
            return INSTANCE!!
        }
    }

    fun writeString(key: String, value: String?) = mShared.edit(true) { putString(key, value) }
    fun readString(key: String): String? = mShared.getString(key, null)
    fun writeBoolean(key: String, value: Boolean) = mShared.edit(true) { putBoolean(key, value) }
    fun readBoolean(key: String): Boolean = mShared.getBoolean(key, false)

}