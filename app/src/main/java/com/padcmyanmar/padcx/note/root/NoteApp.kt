package com.padcmyanmar.padcx.note.root

import android.app.Application
import android.content.Context
import com.padcmyanmar.padcx.note.persistence.db.NoteDB

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-09.
 */

class NoteApp : Application() {
    companion object {
        private lateinit var instance: NoteApp

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this

        super.onCreate()
    }
}