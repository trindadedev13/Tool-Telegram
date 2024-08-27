package org.gampiot.tooltelegram.ui.viewmodels.preferences.d

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import org.gampiot.tooltelegram.repositories.AppPreferencesRepository

class AppPreferencesViewModel(
    private val repo: AppPreferencesRepository
) : ViewModel() {
     val isUseMonet = repo.isUseMonet
     val isUseHighContrast = repo.isUseHighContrast
     
     fun enableMonet (value: Boolean) {
         viewModelScope.launch {
              repo.enableMonet(value)
         }
     }
     
     fun enableHighContrast (value: Boolean) {
         viewModelScope.launch {
              repo.enableHighContrast(value)
         }
     }
}