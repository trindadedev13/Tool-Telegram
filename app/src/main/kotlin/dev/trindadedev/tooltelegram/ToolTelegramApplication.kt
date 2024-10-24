package dev.trindadedev.tooltelegram

import android.app.Application
import dev.trindadedev.tooltelegram.di.appModule
import dev.trindadedev.tooltelegram.di.appPreferencesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ToolTelegramApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ToolTelegramApplication)
            modules(appModule, appPreferencesModule)
        }
    }
}
