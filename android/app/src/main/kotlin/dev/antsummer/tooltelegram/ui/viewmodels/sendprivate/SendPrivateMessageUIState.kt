package dev.antsummer.tooltelegram.ui.viewmodels.sendprivate

import android.content.Context

data class SendPrivateMessageUIState(
    val chatId: String = "-100",
    val token: String = "",
    val message: String = "Messaged by Tool Telegram Remake",
    val isSuccess: Boolean = false
)
