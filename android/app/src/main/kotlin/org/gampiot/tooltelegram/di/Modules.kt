package org.gampiot.tooltelegram.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import org.gampiot.tooltelegram.ui.viewmodels.preferences.d.AppPreferencesViewModel
import org.gampiot.tooltelegram.repositories.AppPreferencesRepository

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