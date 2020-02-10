package com.padcmyanmar.padcx.note.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.padcmyanmar.padcx.note.persistence.typeconverters.DateConverter
import java.util.*

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-10.
 */

@Entity(tableName = "p_note")
@TypeConverters(DateConverter::class)
class NoteVO {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteID: Int = 0

    @ColumnInfo(name = "note_content")
    var noteContent: String = ""

    @ColumnInfo(name = "created_date")
    var createdDate: Date? = null
}