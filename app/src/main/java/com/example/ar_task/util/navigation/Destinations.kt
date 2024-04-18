package com.example.ar_task.util.navigation

sealed class Destinations(val route: String) {
    object SearchScreen : Destinations("SearchScreen")
    object FavoriteScreen : Destinations("FavoriteScreen")
}