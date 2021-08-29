package com.gpaddy.baseandroid

import android.app.Application
import android.content.ContextWrapper
import com.gpaddy.baseandroid.theu.DAO.DataLocalManager
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
            .setContext(applicationContext)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        DataLocalManager.init(applicationContext)

    }
}