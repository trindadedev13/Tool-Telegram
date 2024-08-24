package dev.antsummer.tooltelegram.ui.viewmodels.sendchannel

data class SendChannelMessageUIState(
      val chatId: String = "-100",
      val token: String = "",
      val message: String = "Messaged by Tool Telegram Remake",
      val onChatIdChange: (String) -> Unit = {},
      val onTokenChange: (String) -> Unit = {},
      val onMessageChange: (String) -> Unit = {}
)