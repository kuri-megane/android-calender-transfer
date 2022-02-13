package io.github.kuri_megane.android_calender_transfer

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log


class EventProvider {

    fun getEvent(context: Context): MutableList<EventItem> {
        // 検索対象のカレンダーの _ID
        val targetCalendarId: Long = 7

        // クエリ条件を設定する
        val uri: Uri = CalendarContract.Events.CONTENT_URI
        val selection = "(" + CalendarContract.Events.CALENDAR_ID + " = ?)"
        val selectionArgs = arrayOf(targetCalendarId.toString())
        val sortOrder: String? = null

        // SQLクエリ用 取得リスト
        val eventProjection = EventItemToQuery().getValueArray()

        // クエリを発行してカーソルを取得する
        val cr: ContentResolver = context.contentResolver
        val cur: Cursor = cr.query(
            uri, eventProjection, selection, selectionArgs, sortOrder
        )!!

        // ログ出力 (Header)
        val sbEvents = StringBuilder()
        for (property in eventProjection) {
            sbEvents.append(property).append(',')
        }
        Log.d("予定", sbEvents.toString())

        // イベント情報を保存するリスト
        val eventList: MutableList<EventItem> = mutableListOf()

        // カーソルから各プロパティを取得する
        while (cur.moveToNext()) {

            val event = EventItem()

            // カーソルから各プロパティを取得する
            event.calenderId = cur.getString(0)
            event.organizer = cur.getString(1)
            event.title = cur.getString(2)
            event.eventLocation = cur.getString(3)
            event.description = cur.getString(4)
            event.eventColor = cur.getString(5)
            event.dtstart = cur.getString(6)
            event.dtend = cur.getString(7)
            event.eventTimezone = cur.getString(8)
            event.eventEndTimezone = cur.getString(9)
            event.duration = cur.getString(10)

            // ログ出力 (Body)
            val content = event.getCsvRecord()
            Log.d("予定", content)

            // リストに保存
            eventList.add(event)
        }

        cur.close()
        return eventList
    }
}