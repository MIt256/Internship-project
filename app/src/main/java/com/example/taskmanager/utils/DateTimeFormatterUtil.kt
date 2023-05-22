package com.example.taskmanager.utils

import com.example.taskmanager.utils.DatePatterns.DATE_PATTERN
import com.example.taskmanager.utils.DatePatterns.SIMPLE_DATE
import com.example.taskmanager.utils.DatePatterns.SIMPLE_DATE_PATTERN
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeFormatterUtil {
    val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
    val simpleFormatter = DateTimeFormatter.ofPattern(SIMPLE_DATE_PATTERN)
    val simpleFormatterDate = DateTimeFormatter.ofPattern(SIMPLE_DATE)

    fun toLocalDateTime(string: String) = LocalDateTime.from(formatter.parse(string))

}

object DatePatterns {
    const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"
    const val SIMPLE_DATE_PATTERN = "dd MM yyyy HH:mm"
    const val SIMPLE_DATE = "dd MM yyyy"
    const val SIMPLE_TIME = "HH:mm"
}