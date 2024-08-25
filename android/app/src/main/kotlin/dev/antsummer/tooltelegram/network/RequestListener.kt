package dev.antsummer.tooltelegram.network

// from gampiot-inc/Oak-Store-Android

import dev.antsummer.tooltelegram.R

interface RequestListener {
   fun onResponse (tag: String, response: String, responseHeader: HashMap<String, String>)
   fun onErrorResponse (tag: String, response: String)
}