package dev.antsummer.tooltelegram.data

import android.content.Context
import dev.antsummer.tooltelegram.network.RequestNetwork
import dev.antsummer.tooltelegram.network.RequestListener
import java.io.File

class TelegramMessageSender(private val context: Context) {

    fun sendMessage(
        chatId: String,
        token: String, 
        message: String, 
        callback: Callback
    ) {
        val url = "https://api.telegram.org/bot$token/sendMessage"
        val formData = HashMap<String, Any>().apply {
            put("chat_id", chatId)
            put("text", message)
        }

        val requestNetwork = RequestNetwork(context)
        
        requestNetwork.startRequestNetwork("POST", url, "TelegramAPI", formData, object : RequestListener {
            override fun onResponse(tag: String, response: String, responseHeader: HashMap<String, String>) {
                callback.onSuccess(response)
            }

            override fun onErrorResponse(tag: String, response: String) {
                callback.onError(response)
            }
        })
    }

    fun sendMessageWithImage(
        chatId: String,
        token: String,
        message: String,
        imageType: String,
        photoFile: File?,
        photoUrl: String?,
        topicId: String = "",
        callback: Callback
    ) {
        val url = "https://api.telegram.org/bot$token/sendPhoto"
        val formData = HashMap<String, Any>().apply {
            put("chat_id", chatId)
            put("caption", message)
            when (imageType) {
                "file" -> {
                    photoFile?.let {
                        put("photo", it)
                    } ?: run {
                        callback.onError("No file selected.")
                        return
                    }
                }
                "url" -> {
                    photoUrl?.let {
                        put("photo", it)
                    } ?: run {
                        callback.onError("No URL provided.")
                        return
                    }
                }
                else -> {
                    callback.onError("Invalid image type.")
                    return
                }
            }
            if (topicId.isNotBlank()) {
                put("message_thread_id", topicId)
            }
        }

        val requestNetwork = RequestNetwork(context)

        requestNetwork.startRequestNetwork("POST", url, "TelegramAPI", formData, object : RequestListener {
            override fun onResponse(tag: String, response: String, responseHeader: HashMap<String, String>) {
                callback.onSuccess(response)
            }

            override fun onErrorResponse(tag: String, response: String) {
                callback.onError(response)
            }
        })
    }

    interface Callback {
        fun onSuccess(response: String)
        fun onError(error: String)
    }
}
