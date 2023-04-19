package com.meridiane.betsson

import android.app.Application
import android.content.Context
import com.meridiane.betsson.room.MainDatabase

class MainApp: Application() {

    companion object {
        internal lateinit var INSTANCE: MainApp
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appContext = applicationContext
    }

    val database by lazy { MainDatabase.getDateBase(this) }
}