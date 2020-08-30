package com.lab.notekeeper.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.lab.notekeeper.EXTRA_NOTE_POSITION
import com.lab.notekeeper.POSITION_NOT_SET
import com.lab.notekeeper.R
import com.lab.notekeeper.`class`.CourseInfo
import com.lab.notekeeper.`class`.DataManager
import com.lab.notekeeper.`class`.NoteInfo
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var notePoistion = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        val arrayAdapter =
            ArrayAdapter<CourseInfo>(
                this,
                android.R.layout.simple_spinner_item,
                DataManager.courses.values.toList()
            )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourses.adapter = arrayAdapter

        notePoistion =
            savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?: intent.getIntExtra(
                EXTRA_NOTE_POSITION,
                POSITION_NOT_SET
            )

        if (notePoistion != POSITION_NOT_SET) {
            displayNote();
        } else {
            DataManager.notes.add(NoteInfo())
            notePoistion = DataManager.notes.lastIndex
        }
    }

    private fun displayNote() {
        val note = DataManager.notes[notePoistion]
        textNoteTitle.setText(note.text)
        textNoteText.setText(note.title)

        val coursePosition = DataManager.courses.values.indexOf(note.courseInfo)
        spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        if (DataManager.notes.size - 1 > notePoistion) {
            ++notePoistion
            displayNote()
            invalidateOptionsMenu()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePoistion >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePoistion]
        note.title = textNoteTitle.text.toString();
        note.text = textNoteText.text.toString();
        note.courseInfo = spinnerCourses.selectedItem as CourseInfo
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(EXTRA_NOTE_POSITION, notePoistion)
    }
}