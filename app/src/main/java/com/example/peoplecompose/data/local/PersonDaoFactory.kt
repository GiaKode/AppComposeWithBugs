package com.example.peoplecompose.data.local

import com.example.peoplecompose.MyApplication

object PersonDaoFactory {

    fun getPersonDao(): PersonDao{
        return AppDatabase.getInstance((MyApplication.getContext())).personDao()
    }
}