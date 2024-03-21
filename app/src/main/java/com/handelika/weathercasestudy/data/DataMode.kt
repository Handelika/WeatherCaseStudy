package com.handelika.weathercasestudy.data

class DataMode<T, Boolean, E : Throwable>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: Throwable? = null
)