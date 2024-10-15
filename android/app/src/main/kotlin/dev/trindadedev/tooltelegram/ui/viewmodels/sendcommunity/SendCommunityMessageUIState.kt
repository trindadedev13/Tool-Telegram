package dev.trindadedev.tooltelegram.ui.viewmodels.sendcommunity

data class SendCommunityMessageUIState(
    val chatId: String = "-100",
    val token: String = "",
    val message: String = "Message by Tool Telegram",
    val isSuccess: Boolean? = null,
    val topicId: String = "",
    val imageUrl: String = "",
)
