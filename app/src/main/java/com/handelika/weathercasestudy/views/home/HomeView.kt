package com.handelika.weathercasestudy.views.home

import SpacerUtils
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.handelika.weathercasestudy.models.WeatherData
import com.handelika.weathercasestudy.ui.theme.Blue500
import com.handelika.weathercasestudy.ui.theme.Blue700
import com.handelika.weathercasestudy.ui.theme.White
import com.handelika.weathercasestudy.utils.LoadingIndicator
import com.handelika.weathercasestudy.utils.NetworkImage
import com.handelika.weathercasestudy.utils.TextWidget
import com.handelika.weathercasestudy.utils.formatDate
import com.handelika.weathercasestudy.utils.getStringRes

@Composable
fun HomeView(navController: NavController, query: String?) {

    val homeViewModel = HomeViewModel()
    homeViewModel.getWeatherData(query = query!!)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Blue700
    ) {

        GetData(homeViewModel = homeViewModel, navController = navController)

    }
}

@Composable
fun GetData(homeViewModel: HomeViewModel, navController: NavController) {
    val context = LocalContext.current

    if (homeViewModel.isLoading.value) {

        //Loading
        LoadingIndicator()

    } else {

        if (homeViewModel.weather.value.data != null) {
            Log.d("weatherdata data", homeViewModel.weather.value.data!!.request.toString())

            Column(
                modifier = Modifier
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.End
            ) {

                IconButton(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = {

                        //                  navController.navigate(WeatherNavigationEnum.SearchScreen.name)
                        navController.popBackStack()
                    }
                ) {

                    Box(
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = White,
                            contentDescription = "Search",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .padding(5.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 5.dp,
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = Blue500.copy(alpha = 0.7f))
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        val data = homeViewModel.weather.value.data!!

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextWidget(
                                data = data.request!![0]!!.query!!,
                                textStyle = TextStyle(
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            SpacerUtils(10.dp)
                            Log.d("weatherdata ui", data.toString())
                            if (data.current_condition!!.isNotEmpty()) {

                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically

                                ) {
                                    NetworkImage(
                                        url = data.current_condition.first()!!.weatherIconUrl!!.first()!!.value!!,
                                        context
                                    )
                                    SpacerUtils(dp = 10.dp)
                                    TextWidget(
                                        data = "${data.current_condition.first()!!.temp_C}${
                                            context.getStringRes(
                                                "degree_sign"
                                            )
                                        }",
                                        textStyle = TextStyle(
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
                                }
                            }
                        }
                    }
                }


                //get list
                GetList(homeViewModel.weather.value.data!!.weather, context)
            }
        }
    }


}

@Composable
fun GetList(weatherList: List<WeatherData.Data.Weather?>?, context: Context) {

    LazyColumn(
        modifier = Modifier.padding(30.dp)
    ) {
        items(count = weatherList!!.size) { index ->
            val data = weatherList[index]
            Log.d("weatherdata", data.toString())

            ListItem(data!!, context)
        }
    }
}

@Composable
fun ListItem(data: WeatherData.Data.Weather, context: Context) {
    Card(
        modifier = Modifier
            .padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RectangleShape)
                .background(color = Blue500)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    TextWidget(
                        data = formatDate(data.date!!),
                        textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        TextWidget(
                            data = context.getStringRes("lowest_degree"),
                            textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)

                        )

                        TextWidget(
                            data = "${data.mintempC}${
                                context.getStringRes(
                                    "degree_sign"
                                )
                            }",
                            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal)
                        )

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextWidget(
                            data = context.getStringRes("highest_degree"),
                            textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)

                        )

                        TextWidget(
                            data = "${data.maxtempC}${
                                context.getStringRes(
                                    "degree_sign"
                                )
                            }",
                            textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Normal)

                        )
                    }
                }
            }
        }
    }
}



