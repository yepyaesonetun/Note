package com.padcmyanmar.padcx.note.persistence.typeconverters

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-10.
 */
open class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}