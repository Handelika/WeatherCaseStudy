package com.handelika.weathercasestudy.utils

import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.handelika.weathercasestudy.const.Constants

@Composable
 fun textAlignment() = TextAlign.Center

@Composable
fun TextWidget ( data : String){

 Text(text = data, color = Constants.TEXT_COLOR, textAlign = textAlignment())
}


fun Context.getStringRes(name: String): String {
 val resourceId = resources.getIdentifier(name, "string", packageName)
 return if (resourceId != 0) {
  getString(resourceId)
 } else {
  ""
 }
}

