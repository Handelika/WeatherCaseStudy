package com.handelika.weathercasestudy.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.handelika.weathercasestudy.ui.theme.Blue700
import com.handelika.weathercasestudy.ui.theme.Orange200

@Composable
fun LoadingIndicator () {

    Box(modifier = Modifier.size(300.dp) ,
        contentAlignment = Alignment.Center

    ){
        CircularProgressIndicator(modifier = Modifier
            .size(100.dp)
            .padding(30.dp),
            backgroundColor = Blue700,
            color = Orange200
        )
    }


}