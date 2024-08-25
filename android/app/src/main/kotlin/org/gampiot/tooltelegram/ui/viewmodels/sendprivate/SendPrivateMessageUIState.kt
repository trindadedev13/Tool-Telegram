package org.gampiot.tooltelegram.ui.viewmodels.sendprivate

import android.content.Context

data class SendPrivateMessageUIState(
    val chatId: String = "-100",
    val token: String = "",
    val message: String = "Message by Tool Telegram",
    val isSuccess: Boolean = false
)
