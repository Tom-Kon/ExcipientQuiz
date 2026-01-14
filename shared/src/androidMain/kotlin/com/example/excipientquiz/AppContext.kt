package com.example.excipientquiz

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppContext {
    lateinit var context: Context
        private set

    var activity: Activity? = null

    fun initialize(context: Context) {
        this.context = context.applicationContext
        if (context is Activity) {
            activity = context
        }
    }
}
