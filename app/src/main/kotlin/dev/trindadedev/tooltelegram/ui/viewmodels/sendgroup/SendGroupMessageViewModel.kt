package dev.trindadedev.tooltelegram.ui.viewmodels.sendgroup

import android.content.Context
import androidx.lifecycle.ViewModel
import dev.trindadedev.tooltelegram.data.TelegramMessageSender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SendGroupMessageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SendGroupMessageUIState())
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

    fun onIsSuccessChange(isSuccess: Boolean?) {
        _uiState.update { it.copy(isSuccess = isSuccess) }
    }

    fun onClickToSend(chatId: String, token: String, message: String, context: Context) {
        val sender = TelegramMessageSender(context)
        sender.sendMessage(
            chatId = chatId,
            token = token,
            message = message,
            callback =
                object : TelegramMessageSender.Callback {
                    override fun onSuccess(response: String) {
                        _uiState.update { it.copy(isSuccess = true) }
                    }

                    override fun onError(error: String) {
                        _uiState.update { it.copy(isSuccess = false) }
                    }
                },
        )
    }
}
