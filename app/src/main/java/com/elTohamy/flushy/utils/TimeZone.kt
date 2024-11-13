package com.elTohamy.flushy.utils

import java.util.TimeZone

fun getTimeZone(): String {
    val timeZone = TimeZone.getDefault()

    //val timeZoneDisplayName = timeZone.displayName

    return timeZone.id
}