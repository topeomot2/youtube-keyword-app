package com.topeomot.youtubekeywordapp.data.db.converter

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

object JodaDateTimeConverter {
    var fmt: DateTimeFormatter = ISODateTimeFormat.dateTime()

    @TypeConverter
    @JvmStatic
    fun toDateTime(value: String): DateTime? = fmt.parseDateTime(value)

    @TypeConverter
    @JvmStatic
    fun fromDateTime(date: DateTime): String? = fmt.print(date)
}