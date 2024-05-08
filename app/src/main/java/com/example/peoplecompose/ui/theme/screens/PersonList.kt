package com.example.peoplecompose.ui.theme.screens

import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.example.peoplecompose.data.PersonRepository
import com.example.peoplecompose.data.factories.PersonServiceFactory
import com.example.peoplecompose.data.model.Person
import com.example.peoplecompose.data.model.PicturePerson
import com.example.peoplecompose.data.remote.ApiClient
import com.example.peoplecompose.data.remote.PersonService
import com.example.peoplecompose.ui.theme.shared.InputTextField
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun PeopleListScreen() {

    val searchPeople = remember { mutableStateOf("") }
    val peopleList = remember { mutableStateOf(listOf<Person>()) }
    val results = remember { mutableStateOf<Int?>(null) }

    val personService = PersonServiceFactory.getPersonService()
    val coroutineScope = rememberCoroutineScope()


    Scaffold { paddingValues ->
        Column (

            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputTextField(input = searchPeople, placeholder = "Buscar personas",
                //onValueChange = {newValue -> searchPeople.value = newValue}
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                onClick = {
                    coroutineScope.launch {
                        val searchValue = searchPeople.value.toIntOrNull() ?: 0
                        results.value = searchValue
                        val response = withContext(Dispatchers.IO) {personService.getPersons(searchValue).execute()}
                        peopleList.value = response.body()?.results ?: listOf()
                    }
                }) {

                Text(text="Buscar Personas")

            }


            LazyColumn {
                items(peopleList.value) {
                    person ->
                    PersonItem(person = person) {
                        
                    }
                }

                
            }
        }
    }
}


@Composable
fun PersonImage(url: String) {
    GlideImage(modifier = Modifier.size(92.dp), imageModel = { url })
}

@Composable
fun PersonItem(person: Person, insert: (()->Unit)? = null, delete: (()->Unit)){

    val isFavorite = remember {
        mutableStateOf(false)
    }

    isFavorite.value = person.isFavorite

    Card (modifier=Modifier.padding(4.dp)) {
        Row (modifier = Modifier.fillMaxWidth()
        ){
            PersonImage(url = person.picture.thumbnail)
            Column (modifier = Modifier.padding(5.dp).weight(4f)) {
                Text(modifier = Modifier
                    .padding(4.dp), text = "Nombre: ${person.name.first}")
                Text(modifier = Modifier
                    .padding(4.dp), text = "Apellido: ${person.name.last}")

                Text(modifier = Modifier
                    .padding(4.dp), text = "Email: ${person.email}")

                Text(modifier = Modifier
                    .padding(4.dp), text = "Celular: ${person.cell}")
            }
            //com.skydoves.landscapist.glide.GlideImage(imageModel = { /*TODO*/ })

            IconButton(modifier = Modifier.weight(1f), 
                onClick = { 
                    isFavorite.value= !isFavorite.value
                    person.isFavorite= isFavorite.value
                    if (person.isFavorite) insert?.let { it() } else delete?.let { it() }
                    
                }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null,
                    tint = if(isFavorite.value) MaterialTheme.colorScheme.primary else Color.Red)

            }
        }

    }

}

