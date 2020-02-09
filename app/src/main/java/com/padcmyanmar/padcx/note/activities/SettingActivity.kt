package com.padcmyanmar.padcx.note.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.padcmyanmar.padcx.note.R
import com.padcmyanmar.padcx.note.persistence.sharedPrefs.NotePrefs
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SettingActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        /*
        Choose note display style with List or Grid via
        Radio Group that associated two radio buttons.
        And saved the selected value to Android Shared Preference.
         */
        rdBtnGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            val childCount = radioGroup.childCount
            (0 until childCount)
                .map { radioGroup.getChildAt(it) as RadioButton }
                .filter { it.id == checkedId }
                .forEach {
                    // save display style to sharedPrefs
                    NotePrefs.saveNoteDisplayStyle(it.text.toString())
                    finish() // dismiss setting activity
                }
        }
    }
}
