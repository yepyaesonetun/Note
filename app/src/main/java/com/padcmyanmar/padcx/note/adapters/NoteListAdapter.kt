package com.padcmyanmar.padcx.note.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.padcx.note.R
import com.padcmyanmar.padcx.note.views.viewholders.NoteViewHolder

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-09.
 */

class NoteListAdapter : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // do noting yet
    }
}