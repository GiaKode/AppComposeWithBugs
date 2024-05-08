package com.example.peoplecompose.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.peoplecompose.data.PersonRepository
import com.example.peoplecompose.data.model.Person

@Composable
fun PersonFavoriteList(personRepository: PersonRepository= PersonRepository()){

    val favoritePersons = remember { mutableStateOf(emptyList<Person>()) }

    LaunchedEffect(key1 = Unit) {
        favoritePersons.value = personRepository.getFavoritePersons().map { it.toPerson() }
    }

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            favoritePersons.value.forEach { person ->
                PersonItem(person = person) {

                }

            }
        }
    }
}