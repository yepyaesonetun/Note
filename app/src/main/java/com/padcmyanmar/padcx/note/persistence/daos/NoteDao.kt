package com.padcmyanmar.padcx.note.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.padcmyanmar.padcx.note.data.vos.NoteVO
import java.util.*

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-10.
 */

@Dao
interface NoteDao {
    @Query("SELECT * FROM p_note")
    fun getAllNotes(): LiveData<List<NoteVO>>

    @Query("DELETE FROM p_note")
    fun deleteAll()

    @Delete
    fun deleteNote(note: NoteVO)

    @Insert
    fun insertNote(noteVO: NoteVO)
}