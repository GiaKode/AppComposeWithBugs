package com.example.peoplecompose.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//Navegacion de la aplicacion entre pantallas

@Composable
fun Navigation() {

    //NavController es el encargado de gestionar la navegacion entre pantallas
    val navController = rememberNavController()

    //NavHost es un contenedor de pantallas que permite navegar entre ellas
    NavHost(navController = navController, startDestination = "Home"){

        //composable es un destino de navegacion
        composable("Home"){
            HomeScreen(
                navigateTo = {navController.navigate("PeopleList")},
                navigateTo2 = {navController.navigate("PeopleFavoriteList")}

            )
        }

        composable("PeopleList"){
            // Add screen here
            PeopleListScreen()
        }

        composable("PeopleFavoriteList"){
            // Add screen here
            PersonFavoriteList()
        }


    }
}