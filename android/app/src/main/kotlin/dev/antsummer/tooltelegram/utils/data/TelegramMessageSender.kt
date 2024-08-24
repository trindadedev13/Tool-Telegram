package dev.antsummer.tooltelegram.utils.data

import android.content.Context

import dev.antsummer.tooltelegram.utils.network.RequestNetwork
import dev.antsummer.tooltelegram.utils.network.RequestListener

class TelegramMessageSender(private val context: Context) {

    fun sendMessage(chatId: String, token: String, message: String, callback: SendMessageCallback) {
        val url = "https://api.telegram.org/bot$token/sendMessage"
        val headers = HashMap<String, String>().apply {
            put("Content-Type", "application/x-www-form-urlencoded")
        }

        val params = HashMap<String, String>().apply {
            put("chat_id", chatId)
            put("text", message)
        }
        
        val queryString = params.entries.joinToString("&") { "${it.key}=${it.value}" }

        val requestNetwork = RequestNetwork(context)
        requestNetwork.headersSet(headers)

        requestNetwork.startRequestNetwork("POST", "$url?$queryString", "TelegramAPI", object : RequestListener {
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
