package dev.antsummer.tooltelegram.ui.viewmodels.sendprivate

data class SendPrivateMessageUIState(
      val chatId: String = "-100",
      val token: String = "",
      val message: String = "Messaged by Tool Telegram Remake",
      val isSuccess: Boolean = false,
      val onChatIdChange: (String) -> Unit = {},
      val onTokenChange: (String) -> Unit = {},
      val onMessageChange: (String) -> Unit = {},
      val onClickToSend: (String, String, String) -> Unit {}
)