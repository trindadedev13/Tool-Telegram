package dev.antsummer.tooltelegram.ui.viewmodels.sendprivate

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SendPrivateMessageViewModel : ViewModel() {
     private val _uiState = MutableStateFlow(SendPrivateMessageUIState())
     val uiState = _uiState.asStateFlow()
     
     init {
         _uiState.update { currentState ->
               currentState.copy(
                    onChatIdChange = { chatId ->
                         _uiState.update {
                             it.copy(
                                 chatId = chatId
                             )
                         }
                    },
                    onTokenChange = { token ->
                         _uiState.update {
                             it.copy(
                                 token = token
                             )
                         }
                    },
                    onMessageChange = { message ->
                         _uiState.update {
                             it.copy(
                                 message = message
                             )
                         }
                    }
               )
         }
     }
}