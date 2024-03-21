package com.handelika.weathercasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.handelika.weathercasestudy.navigation.WeatherNavigation
import com.handelika.weathercasestudy.ui.theme.WeatherCaseStudyTheme
import com.handelika.weathercasestudy.views.home.HomeView
import com.handelika.weathercasestudy.views.search.SearchViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            WeatherNavigation(navController)

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        WeatherCaseStudyTheme {
        }
    }

}

