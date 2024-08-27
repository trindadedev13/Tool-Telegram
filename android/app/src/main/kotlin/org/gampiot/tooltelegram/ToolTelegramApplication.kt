package org.gampiot.tooltelegram

import android.app.Application

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

import org.gampiot.tooltelegram.di.appModule
import org.gampiot.tooltelegram.di.appPreferencesModule

class ToolTelegramApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ToolTelegramApplication)
            modules(
                appModule,
                appPreferencesModule
            )
        }
    }

}