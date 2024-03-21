package com.handelika.weathercasestudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.handelika.weathercasestudy.views.home.HomeView
import com.handelika.weathercasestudy.views.search.SearchView

@Composable
fun WeatherNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = WeatherNavigationEnum.MainScreen.name
    ) {
        composable(WeatherNavigationEnum.MainScreen.name) {
            HomeView(navController)
        }

        composable(WeatherNavigationEnum.SearchScreen.name) {
            SearchView(navController)
        }
    }


}