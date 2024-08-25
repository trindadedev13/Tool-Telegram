package dev.antsummer.tooltelegram.network

import android.app.Activity
import android.content.Context

import okhttp3.*

import java.io.File
import java.io.IOException

class RequestNetwork(private val context: Context) {

    private var headers: HashMap<String, String>? = null
    private var requestListener: RequestListener? = null

    fun headersSet(headers: HashMap<String, String>) {
        this.headers = headers
    }
    
    fun startRequestNetwork(
        method: String,
        url: String,
        tag: String,
        formData: HashMap<String, Any>,
        requestListener: RequestListener
    ) {
        this.requestListener = requestListener

        val client = OkHttpClient()

        val requestBuilder = Request.Builder().url(url)
        headers?.forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }

        val requestBody = if (method == "POST" && formData.isNotEmpty()) {
            val multipartBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)

            formData.forEach { (key, value) ->
                when (value) {
                    is File -> multipartBuilder.addFormDataPart(key, value.name, value.asRequestBody())
                    is String -> multipartBuilder.addFormDataPart(key, value)
                    else -> throw IllegalArgumentException("Unsupported value type: ${value::class.java}")
                }
            }
            multipartBuilder.build()
        } else {
            null
        }

        val request = when (method) {
            "GET" -> requestBuilder.get().build()
            "POST" -> requestBuilder.post(requestBody!!).build()
            else -> throw IllegalArgumentException("Unsupported method: $method")
        }

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                (context as? Activity)?.runOnUiThread {
                    requestListener.onErrorResponse(tag, e.message ?: "Unknown error")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string() ?: ""
                    val responseHeaders = response.headers.toMap()
                    (context as? Activity)?.runOnUiThread {
                        requestListener.onResponse(tag, responseBody, responseHeaders)
                    }
                } else {
                    (context as? Activity)?.runOnUiThread {
                        requestListener.onErrorResponse(tag, response.message)
                    }
                }
            }
        })
    }
}

fun Headers.toMap(): HashMap<String, String> {
    val map = HashMap<String, String>()
    for (i in 0 until size) {
        map[name(i)] = value(i)
    }
    return map
}
