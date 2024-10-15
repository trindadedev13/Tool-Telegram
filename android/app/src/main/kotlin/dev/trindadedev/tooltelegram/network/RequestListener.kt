package dev.trindadedev.tooltelegram.network

interface RequestListener {
    fun onResponse(tag: String, response: String, responseHeader: HashMap<String, String>)

    fun onErrorResponse(tag: String, response: String)
}
