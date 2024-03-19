package com.handelika.weathercasestudy.data

class DataMode<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)