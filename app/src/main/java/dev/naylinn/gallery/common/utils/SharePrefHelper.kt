package com.co_op.deli.common.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val KEY_IS_LOGIN = "is_login"
const val KEY_LOGIN_ID = "login_id"
const val KEY_SHOW_SHOWCASE_HOME = "show_case_home"
const val KEY_SHOW_SHOWCASE_ITEM = "show_case_item"
const val KEY_SHOW_SHOWCASE_CART = "show_case_cart"
const val KEY_SHOW_SHOWCASE_FAVORITE = "show_case_favorite"
const val KEY_SHOW_SHOWCASE_CHECKLIST = "show_case_checklist"
const val PREF_NAME = "co_op_deli"

class SharePrefHelper {

    fun getIsLogin(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_IS_LOGIN, false)
    }

    fun setIsLogin(context: Context, isLogin: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putBoolean(KEY_IS_LOGIN, isLogin)
            apply()
        }
    }

    fun getLoginID(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        return sharedPref.getString(KEY_LOGIN_ID, "").toString()
    }

    fun setLoginID(context: Context, value: String) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString(KEY_LOGIN_ID, value)
            apply()
        }
    }

    fun getBooleanValue(context: Context, key: String): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        return sharedPref.getBoolean(key, false)
    }

    fun setBooleanValue(context: Context, key: String, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putBoolean(key, value)
            apply()
        }
    }


    fun clear(context: Context) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            clear()
            apply()
        }
    }


}