package com.lab.notekeeper.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.lab.notekeeper.EXTRA_NOTE_POSITION
import com.lab.notekeeper.R
import com.lab.notekeeper.`class`.DataManager
import com.lab.notekeeper.`class`.NoteInfo
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

        listNotes.adapter =
            ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, DataManager.notes);

        listNotes.setOnItemClickListener { parent, view, position, id ->
            val activityIntent = Intent(this, MainActivity::class.java)
                .putExtra(EXTRA_NOTE_POSITION,position)
            startActivity(activityIntent)
        }
    }

    override fun onResume() {
        super.onResume()

        (listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}