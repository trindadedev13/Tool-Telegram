package org.gampiot.tooltelegram.data

import android.content.Context

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

object Preferences {
    val USE_MONET = booleanPreferencesKey(name = "use_monet")
    val USE_HIGH_CONTRAST = booleanPreferencesKey(name = "use_high_contrast")
}