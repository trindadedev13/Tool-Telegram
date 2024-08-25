package dev.antsummer.tooltelegram.ui.viewmodels.sendcommunity

import android.content.Context

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import dev.antsummer.tooltelegram.data.TelegramMessageSender

class SendCommunityMessageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SendCommunityMessageUIState())
    val uiState = _uiState.asStateFlow()

    fun onChatIdChange(chatId: String) {
        _uiState.update { it.copy(chatId = chatId) }
    }

    fun onTokenChange(token: String) {
        _uiState.update { it.copy(token = token) }
    }

    fun onMessageChange(message: String) {
        _uiState.update { it.copy(message = message) }
    }

    fun onClickToSend(cid: String, token: String, msg: String, context: Context) {
        val sender = TelegramMessageSender(context)
        sender.sendMessage(cid, token, msg, object : TelegramMessageSender.Callback {
            override fun onSuccess(response: String) {
                _uiState.update {
                    it.copy(isSuccess = true)
                }
                // Chame uma função de callback, ou use um outro meio para notificar o sucesso
            }

            override fun onError(error: String) {
                _uiState.update {
                    it.copy(isSuccess = false)
                }
                // Chame uma função de callback, ou use um outro meio para notificar o erro
            }
        })
    }
}
