package dev.trindadedev.tooltelegram.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import dev.trindadedev.tooltelegram.repositories.AppPreferencesRepository
import dev.trindadedev.tooltelegram.ui.viewmodels.preferences.d.AppPreferencesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

const val APP_PREFERENCES = "app_preferences"

val appModule = module {
    singleOf(::AppPreferencesRepository)
    viewModelOf(::AppPreferencesViewModel)
}

val appPreferencesModule = module {
    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile(APP_PREFERENCES)
        }
    }
}
