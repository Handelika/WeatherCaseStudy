package com.handelika.weathercasestudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.handelika.weathercasestudy.ui.theme.Blue700
import com.handelika.weathercasestudy.ui.theme.WeatherCaseStudyTheme
import com.handelika.weathercasestudy.views.home.HomeView

class MainActivity : ComponentActivity() {

    var name : String = "İzmir"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeView()

        }
    }



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        WeatherCaseStudyTheme {
        }
    }

}

