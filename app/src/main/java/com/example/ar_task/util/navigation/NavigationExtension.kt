package com.example.ar_task.util.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateAndDeleteBackStack(route: String) =
    this.navigate(route){
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }

fun NavHostController.navigateAndPopUpTo(route: String,popUpTo : String) =
    this.navigate(route){
        popUpTo(popUpTo)
    }
