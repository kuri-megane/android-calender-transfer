package io.github.kuri_megane.android_calender_transfer

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log

class EventRegister(context: Context) {
    private val query = EventItemToQuery()
    var cr: ContentResolver = context.contentResolver

    // コピー先カレンダーID
    // TODO: カレンダーを変えるときはここの数字を変える
    private var calenderId = 79

    fun registerEvent(events: MutableList<EventItem>) {
        for (e in events) {
            register(e)
        }
    }

    private fun register(event: EventItem) {
        Log.d("登録", event.getCsvRecord())
        val values = ContentValues().apply {
            put(CalendarContract.Events.CALENDAR_ID, calenderId)
            put(query.organizer, event.organizer)
            put(query.title, event.title)
            put(query.eventLocation, event.eventLocation)
            put(query.description, event.description)
            put(query.eventColor, event.eventColor)
            put(query.dtstart, event.dtstart)
            put(query.dtend, event.dtend)
            put(query.eventTimezone, event.eventTimezone)
            put(query.eventEndTimezone, event.eventEndTimezone)
            put(query.duration, event.duration)
            put(query.allDay, event.allDay)
            put(query.rrule, event.rrule)
            put(query.rdate, event.rdate)
            put(query.exrule, event.exrule)
            put(query.exdate, event.exdate)
            put(query.originalId, event.originalId)
            put(query.originalSyncId, event.originalSyncId)
            put(query.originalInstanceTime, event.originalInstanceTime)
            put(query.originalAllDay, event.originalAllDay)
            put(query.accessLevel, event.accessLevel)
            put(query.availability, event.availability)
            put(query.guestsCanModify, event.guestsCanModify)
            put(query.guestsCanInviteOthers, event.guestsCanInviteOthers)
            put(query.guestsCanSeeGuests, event.guestsCanSeeGuests)
            put(query.customAppPackage, event.customAppPackage)
            put(query.customAppURI, event.customAppURI)
            put(query.uid2445, event.uid2445)
        }
        val uri: Uri? = cr.insert(CalendarContract.Events.CONTENT_URI, values)
    }
}