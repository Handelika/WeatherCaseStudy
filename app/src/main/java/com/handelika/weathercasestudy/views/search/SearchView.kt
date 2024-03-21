package com.handelika.weathercasestudy.views.search

import SpacerUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.handelika.weathercasestudy.models.WeatherData
import com.handelika.weathercasestudy.navigation.WeatherNavigationEnum
import com.handelika.weathercasestudy.ui.theme.Blue200
import com.handelika.weathercasestudy.ui.theme.Blue500
import com.handelika.weathercasestudy.ui.theme.Blue700
import com.handelika.weathercasestudy.ui.theme.Orange200
import com.handelika.weathercasestudy.ui.theme.White
import com.handelika.weathercasestudy.utils.TextWidget
import com.handelika.weathercasestudy.utils.getStringRes

@Composable
fun SearchView(navController: NavController, searchViewModel: SearchViewModel = viewModel()
)  {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Blue700
    ) {

        WeatherApp(searchViewModel)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {

            //  Search
            CustomTextField(searchViewModel)

            SpacerUtils(dp = 15.dp)
            //Search results
            SearchText(searchViewModel)

            GetList(searchViewModel = searchViewModel, navController = navController)

        }
    }
}

@Composable
fun WeatherApp(searchViewModel: SearchViewModel = viewModel()){
    val weatherList = searchViewModel.getList()

}

@Composable
fun GetList(
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        items(count = searchViewModel.weatherList.size) { index ->
            val data = searchViewModel.weatherList[index]

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                //Text Card
                CardItem(data = data.data!!.request!!.first()!!,
                    navController = navController)

                //Delete button
                DeleteItemWidget(
                    searchViewModel,
                    index
                )
            }
        }
    }
}

@Composable
private fun DeleteItemWidget(
    searchViewModel: SearchViewModel,
    index: Int,

    ) {

    IconButton(onClick = {
        searchViewModel.removeData(index = index)
    }) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = White,
                contentDescription = "Delete",
                modifier = Modifier.size(30.dp)
            )
        }
    }


}

@Composable
private fun CardItem(
    navController: NavController,
    data: WeatherData.Data.Request?
) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(.9F)
            .clickable(
                onClick = {
                    navController.saveState()
                    navController.navigate("${WeatherNavigationEnum.MainScreen.name}/${data!!.query}")
                }
            ),
        backgroundColor = Blue500
    ) {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            TextWidget(
                data = data!!.query!!,
                textStyle = TextStyle(fontSize = 18.sp)
            )
        }
    }
}

@Composable
fun SearchText(searchViewModel: SearchViewModel) {
    Box(
        contentAlignment = Alignment.Center
    ) {

        if (searchViewModel.textState.value.isNotEmpty()) {
            if (searchViewModel.data.value.data != null) {
                val weather = searchViewModel.data.value.data!!.data

                if (weather != null && !weather.request.isNullOrEmpty()) {
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            // adding search result
                            searchViewModel.addData()

                        }) {
                        TextWidget(
                            data = weather.request.first()!!.query!!,
                            textStyle = TextStyle(fontSize = 18.sp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTextField(searchViewModel: SearchViewModel) {
    val context = LocalContext.current

    val textState = remember {
        mutableStateOf("")
    }

    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    TextField(
        colors = TextFieldDefaults.textFieldColors(
            textColor = White,
            placeholderColor = Blue200,
            disabledLabelColor = Orange200,
            disabledPlaceholderColor = Orange200,
            focusedLabelColor = Orange200,
            disabledTextColor = Orange200,
            disabledIndicatorColor = Orange200,
            unfocusedLabelColor = Orange200
        ),
        modifier = Modifier.fillMaxWidth(),
        value = textState.value,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                searchViewModel.fetchData(query = textState.value)
                searchViewModel.searchData()
                if (searchViewModel.textState.value.isNotEmpty()) {
                    textState.value = ""
                    softwareKeyboardController!!.hide()
                }
            }
        ),
        onValueChange = {
            textState.value = it
        },
        label = { Text(context.getStringRes("search")) },
        placeholder = { Text(context.getStringRes("search_desc")) }
    )
}
