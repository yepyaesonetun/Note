package com.padcmyanmar.padcx.note.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.padcx.note.data.vos.NoteVO
import com.padcmyanmar.padcx.note.persistence.daos.NoteDao

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-10.
 */

@Database(entities = [NoteVO::class], version = 2)
abstract class NoteDB : RoomDatabase() {

    companion object {
        val DB_NAME = "PADC_NOTE_X.DB"
        var dbInstance: NoteDB? = null
        fun getDBInstance(context: Context): NoteDB {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, NoteDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun noteDao(): NoteDao
}