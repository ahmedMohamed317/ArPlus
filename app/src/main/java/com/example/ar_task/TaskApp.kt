package com.example.ar_task

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskApp : Application()

{
    init{
        app = this
    }
    companion object{
        private lateinit var app : TaskApp
        fun getAppContext() = app.applicationContext
    }
}
