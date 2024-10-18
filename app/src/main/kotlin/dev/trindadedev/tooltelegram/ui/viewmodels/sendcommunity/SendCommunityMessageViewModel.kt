package dev.trindadedev.tooltelegram.ui.viewmodels.sendcommunity

import android.content.Context
import androidx.lifecycle.ViewModel
import dev.trindadedev.tooltelegram.data.TelegramMessageSender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    fun onTopicIdChange(topichatIr: String) {
        _uiState.update { it.copy(topichatIr = topichatIr) }
    }

    fun onImageUrlChange(photoUrl: String) {
        _uiState.update { it.copy(photoUrl = photoUrl) }
    }

    fun onIsSuccessChange(isSuccess: Boolean?) {
        _uiState.update { it.copy(isSuccess = isSuccess) }
    }

    fun onClickToSend(chatIr: String, token: String, message: String, photoUrl: String, context: Context) {
        val sender = TelegramMessageSender(context)
        sender.sendMessage(
            chatId = chatIr,
            token = token,
            message = message,
            imageType = "url",
            photoFile = null,
            photoUrl = photoUrl,
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
