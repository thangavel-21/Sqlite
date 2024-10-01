package com.thangavel.sqlite

import android.app.Application

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        notesDB = NotesDatabaseHelper(applicationContext)
    }

    companion object {
        lateinit var notesDB: NotesDatabaseHelper
    }
}