package org.lowsummer.tooltelegram.ui.viewmodels.sendcommunity

import android.content.Context

data class SendCommunityMessageUIState(
    val chatId: String = "-100",
    val token: String = "",
    val message: String = "Messaged by Tool Telegram Remake",
    val isSuccess: Boolean = false,
    val topicId: String = "",
    val imageUrl: String = ""
)
