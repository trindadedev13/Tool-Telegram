package dev.trindadedev.tooltelegram.network

import android.content.Context
import android.app.Activity

import okhttp3.*

import java.io.IOException

class RequestNetwork(private val context: Context) {

    private var headers: HashMap<String, String>? = null
    private var requestListener: RequestListener? = null

    fun headersSet(headers: HashMap<String, String>) {
        this.headers = headers
    }

    fun startRequestNetwork(method: String, url: String, tag: String, requestListener: RequestListener) {
        startRequestNetwork(method, url, tag, null, requestListener)
    }

    fun startRequestNetwork(method: String, url: String, tag: String, formData: HashMap<String, Any>?, requestListener: RequestListener) {
        this.requestListener = requestListener
        val client = OkHttpClient()

        val requestBuilder = Request.Builder().url(url)
        headers?.forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }

        if (method == "POST" && formData != null) {
            val formBodyBuilder = FormBody.Builder()
            formData.forEach { (key, value) ->
                formBodyBuilder.add(key, value.toString())
            }
            requestBuilder.post(formBodyBuilder.build())
        }

        val request = requestBuilder.build()

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

    fun startRequestNetwork(method: String, url: String, tag: String, requestBody: RequestBody, requestListener: RequestListener) {
        this.requestListener = requestListener
        val client = OkHttpClient()

        val requestBuilder = Request.Builder().url(url)
        headers?.forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }

        if (method == "POST") {
            requestBuilder.post(requestBody)
        }

        val request = requestBuilder.build()

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
