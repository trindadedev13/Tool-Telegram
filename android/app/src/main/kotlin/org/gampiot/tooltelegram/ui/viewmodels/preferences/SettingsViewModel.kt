package org.gampiot.tooltelegram.ui.viewmodels.preferences

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUIState())
    val uiState = _uiState.asStateFlow()
    
    fun onIsUseMonetChange(isUseMonet: Boolean) {
        _uiState.update {
             it.copy(isUseMonet = isUseMonet)
        }
    }
}
