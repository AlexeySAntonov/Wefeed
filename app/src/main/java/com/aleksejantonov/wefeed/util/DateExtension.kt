package com.aleksejantonov.wefeed.util

import java.util.Calendar

val months = arrayOf(
    "января", "февраля", "марта", "апреля", "мая", "июня",
    "июля", "августа", "сентября", "октября", "ноября", "декабря"
)

fun Long.toReadableDate(): String {
  val calendar = Calendar.getInstance()
  calendar.timeInMillis = this
  return "" +
      "${calendar.get(Calendar.DAY_OF_MONTH)} " +
      "${months[calendar.get(Calendar.MONTH)]} в " +
      "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
}