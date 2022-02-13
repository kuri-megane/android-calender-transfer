package io.github.kuri_megane.android_calender_transfer

import android.provider.CalendarContract

class CalenderItemToQuery {

    private var id: String = CalendarContract.Calendars._ID
    private val name : String = CalendarContract.Calendars.NAME

    fun getValueArray(): Array<String> {
        return arrayOf(
            id,
            name
        )
    }

}