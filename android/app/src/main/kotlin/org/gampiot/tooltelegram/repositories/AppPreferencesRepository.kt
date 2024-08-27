package org.gampiot.tooltelegram.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.prefetences.core.booleanPreferencesKey

import kotlinx.coroutines.flow.map

private val isUseMonetPreference = booleanPreferencesKey("is_use_monet")
private val isUseHighContrastPreference = booleanPreferencesKey("is_use_high_contrast")

class AppPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
     val isUseMonet = dataStore.data
          .map {
              it[isUseMonetPreference] ?: true
          }
     val isUseHighContrast = dataStore.data
          .map {
             it[isUseHighContrastPreference] ?: false
          }
        
     suspend fun enableMonet (value: Boolean) {
         dataStore.edit { preferences ->
             preferences[isUseMonetPreference] = value
         }
     }
     
     suspend fun enableHighContrast (value: Boolean) {
         dataStore.edit { preferences ->
             preferences[isUseMonetPreference] = value
         }
     }
}