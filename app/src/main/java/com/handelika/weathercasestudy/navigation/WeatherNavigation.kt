package com.handelika.weathercasestudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.handelika.weathercasestudy.views.home.HomeView
import com.handelika.weathercasestudy.views.search.SearchView

@Composable
fun WeatherNavigation(navController: NavHostController) {
    navController.saveState()

    NavHost(
        navController = navController,
        startDestination = WeatherNavigationEnum.SearchScreen.name
    ) {

        composable(WeatherNavigationEnum.SearchScreen.name) {
            SearchView(navController)
        }

        val route = WeatherNavigationEnum.MainScreen.name
        composable(

            "$route/{query}",
            arguments = listOf(
                navArgument(name = "query"){
                    type = NavType.StringType
                })) { navBack ->
            navBack.arguments?.getString("query").let { query ->

                HomeView(
                    navController = navController,
                    query = query
                )
            }
        }

    }


}