package com.padcmyanmar.padcx.note.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.padcx.note.R
import com.padcmyanmar.padcx.note.persistence.sharedPrefs.NotePrefs

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var displayStyle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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
