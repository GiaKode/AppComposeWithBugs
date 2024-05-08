package com.example.peoplecompose.data.factories

import com.example.peoplecompose.data.remote.ApiClient
import com.example.peoplecompose.data.remote.PersonService

object PersonServiceFactory {


    fun getPersonService(): PersonService {
        return ApiClient.getRetrofit().create(PersonService::class.java)
    }
}