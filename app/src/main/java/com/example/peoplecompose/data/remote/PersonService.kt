package com.example.peoplecompose.data.remote

import com.example.peoplecompose.data.model.PersonWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {
    @GET("api/")
    fun getPersons(@Query("results") results: Int): Call<PersonWrapper>
}