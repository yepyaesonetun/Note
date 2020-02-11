package com.padcmyanmar.padcx.note.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.padcmyanmar.padcx.note.R
import com.padcmyanmar.padcx.note.adapters.NoteListAdapter
import com.padcmyanmar.padcx.note.data.models.NoteViewModel
import com.padcmyanmar.padcx.note.data.vos.NoteVO
import com.padcmyanmar.padcx.note.persistence.db.NoteDB
import com.padcmyanmar.padcx.note.persistence.sharedPrefs.NotePrefs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDisplayStyle: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var mTheDB: NoteDB

    private lateinit var mAdapter: NoteListAdapter
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        linearLayoutManager = LinearLayoutManager(this)
        gridLayoutManager = GridLayoutManager(this, 2)

        mTheDB = NoteDB.getDBInstance(this)

        mAdapter = NoteListAdapter()

        // live data
//        mTheDB.noteDao().getAllNotes().observe(this,
//            Observer { mAdapter.setNotes(it) })

//         with viewModel
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this,
            Observer { mAdapter.setNotes(it) })

        rvNoteList.adapter = mAdapter

        fab.setOnClickListener {
            showAddNoteDialog()
        }
    }

    @SuppressLint("InflateParams")
    private fun showAddNoteDialog() {
        val notePromptView = LayoutInflater.from(this).inflate(R.layout.view_note_input, null)
        val edtNote = notePromptView.findViewById<TextInputEditText>(R.id.tiEdtNote)

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setView(notePromptView)

        alertDialog.setCancelable(false)
            .setPositiveButton("Add") { dialogInterface
                                        , _ ->

                val note = NoteVO()
                note.noteContent = edtNote.text.toString()
                note.createdDate = Date()

                mTheDB.noteDao().insertNote(note)
                dialogInterface.cancel()

            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.cancel()
            }

        alertDialog.show()
    }

    override fun onResume() {

        mDisplayStyle = NotePrefs.getNoteDisplayStyle()!!

        when (mDisplayStyle) {
            resources.getString(R.string.rd_btn_list_txt_value) ->
                rvNoteList.layoutManager = linearLayoutManager
            resources.getString(R.string.rd_btn_grid_txt_value) ->
                rvNoteList.layoutManager = gridLayoutManager
        }

        mAdapter.notifyDataSetChanged()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(SettingActivity.getIntent(this))
                return true
            }
            R.id.action_delete -> {
                showConfirmationDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Are You Sure?")
        alertDialog.setMessage("Delete All Notes! This cannot be undone.")
        alertDialog.setPositiveButton("Delete All") { dialogInterface, _ ->

            mTheDB.noteDao().deleteAll()
            dialogInterface.cancel()

        }.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.cancel()
        }
        alertDialog.show()
    }
}
