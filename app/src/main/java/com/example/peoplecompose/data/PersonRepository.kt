package com.example.peoplecompose.data

import android.util.Log
import com.example.peoplecompose.data.factories.PersonServiceFactory
import com.example.peoplecompose.data.local.PersonDao
import com.example.peoplecompose.data.local.PersonDaoFactory
import com.example.peoplecompose.data.local.PersonEntity
import com.example.peoplecompose.data.model.Person
import com.example.peoplecompose.data.model.PersonWrapper
import com.example.peoplecompose.data.remote.PersonService
import retrofit2.Response
import retrofit2.Retrofit

class PersonRepository(private val personService: PersonService = PersonServiceFactory.getPersonService(),
    private val personDao: PersonDao = PersonDaoFactory.getPersonDao()) {

    fun insert(person: Person){
        val personEntity = person.toPersonEntity()
        personDao.insert(personEntity)
    }

    fun delete(person: Person){
        val personEntity = person.toPersonEntity()
        personDao.delete(personEntity)
    }

    fun isFavorite(id: Int): Boolean{
        return personDao.fetchById(id) != null

    }

    fun getFavoritePersons(): List<PersonEntity> {
        val personEntities = personDao.fetchAll()
        return personEntities ?: emptyList()
    }



    suspend fun getPersons(results: Int): Response<PersonWrapper> {
            val response = personService.getPersons(results).execute()
            if (response.isSuccessful) {
                return response
            } else {
                Log.e("PersonRepository", "Error ${response.code()} ${response.message()}")
                throw Exception("Error ${response.code()} ${response.message()}")
            }
        }
}
