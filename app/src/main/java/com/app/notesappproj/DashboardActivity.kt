package com.app.notesappproj

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.notesappproj.Room.AppDatabase
import com.app.notesappproj.Room.Dao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardActivity : AppCompatActivity() {
    private lateinit var addfab:FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<String>
    private lateinit var dao:Dao
    private lateinit var empty:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        addfab=findViewById(R.id.fab)
        empty=findViewById(R.id.empty)
        recyclerView=findViewById(R.id.recycler)

        addfab.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        })

        val db=AppDatabase.getInstance(applicationContext)
        dao=db.Dao()

        recyclerView.layoutManager=LinearLayoutManager(this)

        list=ArrayList()

        dao.getallData().observe(this){notelist->

            if (notelist.isEmpty()){
                empty.visibility=View.VISIBLE
                recyclerView.visibility=View.GONE
            }else{
                empty.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE
                recyclerView.adapter=Adapter(this,notelist)
            }

        }

    }
}