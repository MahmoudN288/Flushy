package com.elTohamy.flushy.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun stringToLocalDate(date: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", getLocale())
    return LocalDate.parse(date, formatter)
}