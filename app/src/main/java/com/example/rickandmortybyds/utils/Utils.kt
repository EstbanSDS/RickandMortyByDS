package com.example.rickandmortybyds.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun String.getDateHeader(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}
