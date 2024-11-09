package com.app.notesappproj

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.notesappproj.Room.AppDatabase
import com.app.notesappproj.Room.Dao
import com.app.notesappproj.Room.Data

class Update : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase
    private lateinit var dao:Dao
    private var noteId :Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val db=AppDatabase.getInstance(applicationContext)
        dao=db.Dao()


        appDatabase=AppDatabase.getInstance(this)
        dao=appDatabase.Dao()

        val titleEdittext=findViewById<EditText>(R.id.noteTitleDetail)
        val descriptionEdittext=findViewById<EditText>(R.id.noteDescriptionDetail)
        val updateButton = findViewById<Button>(R.id.update)

        noteId=intent.getIntExtra("notes",-1)

        if (noteId!=-1){
            dao.getallData().observe(this,{notes->
                val note = notes.find { it.id==noteId }
                note?.let {
                    titleEdittext.setText(it.title)
                    descriptionEdittext.setText(it.description)
                }
            })
        }

        updateButton.setOnClickListener {
            val updatenote=Data(
                id=noteId,
                title = titleEdittext.text.toString(),
                description = descriptionEdittext.text.toString()
            )

            dao.updateData(updatenote)

        }

    }
}