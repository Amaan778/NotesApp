package com.app.notesappproj

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.notesappproj.Room.AppDatabase
import com.app.notesappproj.Room.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Adapter(val context: Context, val list :List<Data>) :RecyclerView.Adapter<Adapter.Viewholder>(){
    class Viewholder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val name=itemview.findViewById<TextView>(R.id.title)
        val idss=itemview.findViewById<TextView>(R.id.idsss)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(LayoutInflater.from(context).inflate(R.layout.layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val notespositiion=list[position]
        holder.idss.text=notespositiion.id.toString()
        holder.name.text=notespositiion.title

        holder.itemView.setOnClickListener{
            val intent=Intent(context, Update::class.java).apply {
                putExtra("notes",list[position].id)
                flags=Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }

        val dao=AppDatabase.getInstance(context).Dao()

        holder.itemView.setOnLongClickListener {
            if (context is Activity){
                AlertDialog.Builder(context)
                    .setTitle("Delete Note")
                    .setMessage("Are you sure you want to delete this note")
                    .setPositiveButton("Yes"){dialog, which->
                        GlobalScope.launch(Dispatchers.IO) {
                            dao.deleteData(Data(list[position].id,list[position].title,list[position].description))
                        }
                    }
                    .setNegativeButton("No",null)
                    .show()
            }
            else{
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
            }

            true
        }

    }
}