package org.gampiot.tooltelegram.ui.viewmodels.sendcommunity

import android.content.Context

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import org.gampiot.tooltelegram.data.TelegramMessageSender

class SendCommunityMessageViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SendCommunityMessageUIState())
    val uiState = _uiState.asStateFlow()

    fun onChatIdChange(chatId: String) {
        _uiState.update { 
            it.copy(chatId = chatId) 
        }
    }

    fun onTokenChange(token: String) {
        _uiState.update {
            it.copy(token = token) 
        }
    }

    fun onMessageChange(message: String) {
        _uiState.update { 
            it.copy(message = message)
        }
    }
    
    fun onTopicIdChange(topicId: String) {
        _uiState.update {
            it.copy(topicId = topicId)
        }
    }
    
    fun onImageUrlChange(imageUrl: String) {
        _uiState.update {
            it.copy(imageUrl = imageUrl)
        }
    }
    
    fun onIsSuccessChange(isSuccess: Boolean) {
        _uiState.update { 
            it.copy(isSuccess = isSuccess)
        }
    }

    fun onClickToSend(cid: String, token: String, msg: String, imageUrl: String, context: Context) {
        val sender = TelegramMessageSender(context)
        sender.sendMessageWithImage(
           chatId = cid, 
           token = token, 
           message = msg,
           imageType = "url", 
           photoFile = null,
           photoUrl = imageUrl,
           callback = object : TelegramMessageSender.Callback {
                override fun onSuccess(response: String) {
                    _uiState.update {
                        it.copy(isSuccess = true)
                    }
                }
                override fun onError(error: String) {
                    _uiState.update {
                        it.copy(isSuccess = false)
                    }
               }
           }
        )
    }
}
