package com.handelika.weathercasestudy.views.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.handelika.weathercasestudy.R
import com.handelika.weathercasestudy.models.WeatherElement
import com.handelika.weathercasestudy.ui.theme.Blue500
import com.handelika.weathercasestudy.ui.theme.Blue700
import com.handelika.weathercasestudy.utils.LoadingIndicator
import com.handelika.weathercasestudy.utils.TextWidget
import com.handelika.weathercasestudy.utils.formatDate
import com.handelika.weathercasestudy.utils.getStringRes

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeView() {

    var homeViewModel = HomeViewModel()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Blue700
    ) {

        GetData(homeViewModel = homeViewModel)

    }

}

@Composable
fun GetData(homeViewModel: HomeViewModel) {

    homeViewModel.FetchData()

    if (homeViewModel.isLoading.value) {

        //Loading
        LoadingIndicator()

    } else {
        Log.d("weatherdata", homeViewModel.weather.value.data.toString())
        val context = LocalContext.current

        Column {

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 30.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 5.dp,
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Blue500.copy(alpha = 0.7f))
                        .fillMaxWidth()
                        .padding(50.dp),
                    contentAlignment = Alignment.Center
                ) {

                    val data = homeViewModel.weather.value.data

                    Column {
                        TextWidget(
                            data = data!!.request!![0].query!!
                        )

                        if (data.currentCondition != null) {
                            NetworkImage(
                                url = data.currentCondition[0].weatherIconURL!![0].value!!,
                                context
                            )
                        }

                    }
                }
            }


            //get list
            GetList(homeViewModel.weather.value.data!!.weather!!, context)
        }


    }
}

@Composable
fun GetList(weatherList: List<WeatherElement>, context: Context) {

    LazyColumn(
        modifier = Modifier.padding(30.dp)
    ) {
        items(count = weatherList.size) { index ->
            val data = weatherList[index]
            Log.d("weatherdata", data.toString())

            ListItem(data, context)
        }
    }
}

@Composable
fun ListItem(data: WeatherElement, context: Context) {
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
                    TextWidget(data = formatDate(data.date!!))

                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    TextWidget(
                        data = "${context.getStringRes("lowest_degree")}\n${data.mintempC!!}${
                            context.getStringRes(
                                "degree_sign"
                            )
                        }"
                    )
                    TextWidget(
                        data = "${context.getStringRes("highest_degree")}\n${data.maxtempC!!}${
                            context.getStringRes(
                                "degree_sign"
                            )
                        }"
                    )
                }
            }
        }
    }
}

@Composable
fun NetworkImage(url: String, context: Context) {

    if (url.isNotEmpty()) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            error = null,
            imageLoader = ImageLoader(context = context),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "stringResource(R.string.description)",
            contentScale = ContentScale.Crop,
        )
    }

}

