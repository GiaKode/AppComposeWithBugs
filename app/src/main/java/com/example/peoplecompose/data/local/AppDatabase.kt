package com.example.peoplecompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {

                instance = Room.databaseBuilder(context,AppDatabase::class.java, "PersonDB")
                    .allowMainThreadQueries()
                    .build()

            }
            return instance as AppDatabase
        }

    }

}