package com.example.peoplecompose.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//Pantalla principal de la aplicación

@Composable
fun HomeScreen(navigateTo: () -> Unit, navigateTo2: () -> Unit ) {

    Scaffold {paddingValues ->

        // Column es un componente que nos permite apilar elementos verticalmente
        Column (
            modifier = Modifier
            .fillMaxSize() // maximo tamaño de la pantalla
            .padding(paddingValues),//espacio entre los elementos de la pantalla
            verticalArrangement = Arrangement.Center, //centra los elementos verticalmente
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally //centra los elementos horizontalmente
        )
        {

            //Boton para navegar a la pantalla de personas
            Button(onClick = { navigateTo() }) {
                Text("Personas")
            }
            //Boton para navegar a la pantalla de favoritos
            Button(onClick = {
                navigateTo2()
            }) {
                Text("Favoritos")
            }

        }
    }

}