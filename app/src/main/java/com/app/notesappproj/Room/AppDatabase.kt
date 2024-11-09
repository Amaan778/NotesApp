package com.app.notesappproj.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun Dao():Dao

    companion object{

        @Volatile
        private var INSTANCE:AppDatabase?=null

        fun getInstance(context: Context):AppDatabase{
            return INSTANCE ?: synchronized(lock = this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Datas"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE=instance
                instance
            }
        }

    }

}