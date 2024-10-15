package dev.trindadedev.tooltelegram.data

import android.content.Context

import dev.trindadedev.tooltelegram.network.RequestNetwork
import dev.trindadedev.tooltelegram.network.RequestListener

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

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

        val multipartBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("chat_id", chatId)
            .addFormDataPart("caption", message)

        when (imageType) {
            "file" -> {
                photoFile?.let { file ->
                    val mediaType = "image/*".toMediaTypeOrNull()
                    val fileBody = file.asRequestBody(mediaType)
                    multipartBuilder.addFormDataPart("photo", file.name, fileBody)
                } ?: run {
                    callback.onError("No file selected.")
                    return
                }
            }
            "url" -> {
                photoUrl?.let { url ->
                    multipartBuilder.addFormDataPart("photo", url)
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
            multipartBuilder.addFormDataPart("message_thread_id", topicId)
        }

        val requestBody = multipartBuilder.build()

        val requestNetwork = RequestNetwork(context)

        requestNetwork.startRequestNetwork("POST", url, "TelegramAPI", requestBody, object : RequestListener {
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
