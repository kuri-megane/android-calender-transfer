package io.github.kuri_megane.android_calender_transfer

import android.provider.CalendarContract

class CalenderListItem {

    private var id: String = CalendarContract.Calendars._ID
    private val name : String = CalendarContract.Calendars.NAME

    fun getList(): Array<String>{
        return arrayOf(id, name)
    }

}