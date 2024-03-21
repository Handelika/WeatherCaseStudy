package com.handelika.weathercasestudy.utils

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.handelika.weathercasestudy.R

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