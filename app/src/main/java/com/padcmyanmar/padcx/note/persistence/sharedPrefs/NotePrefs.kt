package com.padcmyanmar.padcx.note.persistence.sharedPrefs

import android.content.Context
import com.padcmyanmar.padcx.note.R
import com.padcmyanmar.padcx.note.root.NoteApp

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-09.
 */

object NotePrefs {
    private const val NOTE_SHARED_PREFS = "NOTE_SHARED_PREFS"
    private const val SHARED_PREFS_NOTE_DISPLAY_STYLE = "SHARED_PREFS_NOTE_DISPLAY_STYLE"

    private fun sharedPrefs() =
        NoteApp.getAppContext().getSharedPreferences(NOTE_SHARED_PREFS, Context.MODE_PRIVATE)


    /**
     * @param styleValueString: this is for note display style like list or gird
     */
    fun saveNoteDisplayStyle(styleValueString: String) {
        val editor = sharedPrefs().edit()
        editor.putString(SHARED_PREFS_NOTE_DISPLAY_STYLE, styleValueString)
            .apply()
    }

    fun getNoteDisplayStyle() = sharedPrefs().getString(
        SHARED_PREFS_NOTE_DISPLAY_STYLE,
        NoteApp.getAppContext().resources.getString(R.string.rd_btn_list_txt_value)
    )
}