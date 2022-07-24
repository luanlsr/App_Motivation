package com.example.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecutiryPreferences(context: Context) {
    private val security: SharedPreferences =
        context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, srt: String){
        security.edit().putString(key, srt).apply()
    }

    fun getString(key: String): String {
        return security.getString(key, "") ?: ""
    }
}