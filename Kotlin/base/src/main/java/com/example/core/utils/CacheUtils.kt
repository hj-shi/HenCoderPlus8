package com.example.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R

class CacheUtils {
    @SuppressLint("StaticFieldLeak")
    companion object {
        private val context = BaseApplication.currentApplication()
        private val SP =
            context?.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

        // 函数简化，返回类型 Unit
        fun save(key: String, value: String) = SP?.edit()?.putString(key, value)?.apply()

        // 函数简化, 返回类型 String？
        fun get(key: String) = SP?.getString(key, null)

    }

}