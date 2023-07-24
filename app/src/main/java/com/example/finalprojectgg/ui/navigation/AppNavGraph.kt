package com.example.finalprojectgg.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalprojectgg.ui.screens.mapdisaster.MapDisasterScreen
import com.example.finalprojectgg.ui.screens.profile.ProfileScreen
import com.example.finalprojectgg.ui.screens.mapdisaster.SearchDisasterScreen
import com.example.finalprojectgg.ui.screens.splash.SplashScreen
import com.example.finalprojectgg.ui.viewmodel.MainViewModel

@Composable
fun AppNavGraph(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {

        composable(
            route = Screens.Splash.route
        ) {
            SplashScreen {
                navController.navigate(Screens.ROOT_ROUTE)
            }

        }

        navigation(
            startDestination = Screens.MapDisaster.route,
            route = Screens.ROOT_ROUTE
        ) {

            composable(
                route = Screens.MapDisaster.route
            ) {
                MapDisasterScreen(paddingValues, viewModel) {
                    navController.navigate(Screens.MapDisasterSearch.route)
                }
            }
            composable(
                route = Screens.MapDisasterSearch.route
            ) {
                SearchDisasterScreen()
            }
            composable(
                route = Screens.Profile.route
            ) {
                ProfileScreen()
            }

        }
    }
}