package org.gampiot.tooltelegram.network

import org.gampiot.tooltelegram.R

interface RequestListener {
   fun onResponse (tag: String, response: String, responseHeader: HashMap<String, String>)
   fun onErrorResponse (tag: String, response: String)
}