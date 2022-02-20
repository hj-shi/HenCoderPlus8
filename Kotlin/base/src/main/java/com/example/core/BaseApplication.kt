package com.example.core

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull

class BaseApplication : Application() {
    companion object {
        var currentApplication: Context? = null

        @NonNull
        fun currentApplication(): Context? {
            return currentApplication;
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }
}