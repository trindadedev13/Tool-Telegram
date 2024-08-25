package org.lowsummer.tooltelegram.network

// from gampiot-inc/Oak-Store-Android

import org.lowsummer.tooltelegram.R

interface RequestListener {
   fun onResponse (tag: String, response: String, responseHeader: HashMap<String, String>)
   fun onErrorResponse (tag: String, response: String)
}