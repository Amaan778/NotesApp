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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var data:EditText
    private lateinit var description:EditText
    private lateinit var save:Button
    private lateinit var dao:Dao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        data=findViewById(R.id.data)
        description=findViewById(R.id.datadescription)
        save=findViewById(R.id.save)

        val db=AppDatabase.getInstance(applicationContext)
        dao=db.Dao()

        save.setOnClickListener {

            val title=data.text.toString()
            val descriptions=description.text.toString()

            val adding=Data(title=title, description = descriptions)
            GlobalScope.launch(Dispatchers.IO) {
                dao.insertData(adding)

                data.text.clear()
                description.text.clear()
            }

        }

    }
}