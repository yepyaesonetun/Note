package com.padcmyanmar.padcx.note.data.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.note.data.vos.NoteVO
import com.padcmyanmar.padcx.note.persistence.db.NoteDB

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-11.
 */

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    var allNotes: LiveData<List<NoteVO>> =
        NoteDB.getDBInstance(application.applicationContext).noteDao().getAllNotes()

}