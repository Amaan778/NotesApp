package com.app.notesappproj.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Query("SELECT * FROM data")
    fun getallData():LiveData<List<Data>>

    @Insert
    fun insertData(data: Data)

    @Update
    fun updateData(data: Data)

    @Delete
    fun deleteData(data: Data)

}