package com.padcmyanmar.padcx.note.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.padcx.note.R
import com.padcmyanmar.padcx.note.adapters.NoteListAdapter
import com.padcmyanmar.padcx.note.persistence.sharedPrefs.NotePrefs

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var displayStyle: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        linearLayoutManager = LinearLayoutManager(this)
        gridLayoutManager = GridLayoutManager(this, 2)

        val adapter = NoteListAdapter()
        rvNoteList.adapter = adapter

        fab.setOnClickListener { view ->

            val snackBar = Snackbar.make(view, displayStyle, Snackbar.LENGTH_INDEFINITE)
            snackBar.setAction("Dismiss") {
                snackBar.dismiss()
            }
            snackBar.show()

        }
    }

    override fun onResume() {
        displayStyle = NotePrefs.getNoteDisplayStyle()!!

        when (displayStyle) {
            resources.getString(R.string.rd_btn_list_txt_value) ->
                rvNoteList.layoutManager = linearLayoutManager
            resources.getString(R.string.rd_btn_grid_txt_value) ->
                rvNoteList.layoutManager = gridLayoutManager
        }

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
            else -> super.onOptionsItemSelected(item)
        }
    }
}
