package com.example.peoplecompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {

    @Insert
    fun insert(person: PersonEntity)

    @Delete
    fun delete(person: PersonEntity)

    @Query("select * from persons where id = :id")
    fun fetchById(id: Int): PersonEntity?

    @Query("select * from persons")
    fun fetchAll(): List<PersonEntity>?

}