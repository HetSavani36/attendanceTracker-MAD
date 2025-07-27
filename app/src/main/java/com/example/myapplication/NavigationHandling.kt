package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationHandling() {

    val navController: NavHostController = rememberNavController()
    val timeTableViewModel: TimeTableViewModel= viewModel()

    NavHost(navController=navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route){
            HomeScreen(timeTableViewModel)
        }
    }

}

sealed class Screen(val route: String){
    object HomeScreen: Screen("HomeScreen")
}