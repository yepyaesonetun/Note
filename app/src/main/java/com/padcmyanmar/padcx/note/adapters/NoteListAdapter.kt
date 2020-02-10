package com.padcmyanmar.padcx.note.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.padcx.note.R
import com.padcmyanmar.padcx.note.data.vos.NoteVO
import com.padcmyanmar.padcx.note.views.viewholders.NoteViewHolder
import kotlinx.android.synthetic.main.item_note.view.*

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-09.
 */

class NoteListAdapter(var noteList: List<NoteVO>) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.itemView.tvNoteContent.text = note.noteContent
        holder.itemView.tvNoteRecordedDate.text = note.createdDate.toString()
    }
}