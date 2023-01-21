package io.github.kuri_megane.android_calender_transfer

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log


class EventReader {

    fun getEvent(context: Context): MutableList<EventItem> {
        // コピー元のカレンダーのID
        // TODO: カレンダーを変えるときはここの数字を変える
        val targetCalendarId: Long = 70

        // クエリ条件を設定する
        val uri: Uri = CalendarContract.Events.CONTENT_URI

//        val selection = "(" + CalendarContract.Events.CALENDAR_ID + " = ?)"
//        val selectionArgs = arrayOf(targetCalendarId.toString())

        val selection = "((${CalendarContract.Events.CALENDAR_ID } = ?) AND (" +
                "${CalendarContract.Events.DTSTART} >= ?) AND (" +
                "${CalendarContract.Events.DTSTART} <= ?))"
        val selectionArgs = arrayOf(
            targetCalendarId.toString(),  // CALENDAR_ID
            "1554044400000"  // 取得開始日 2019-04-01 00:00:00
//            "1648738800000"  // 取得開始日 2022-04-01 00:00:00
            ,
//            "1585666799000"  // 取得終了日 2020-03-31 23:59:59
            "1680274799000"  // 取得終了日 2023-03-31 23:59:59
        )

        val sortOrder = "${CalendarContract.Events.DTSTART} ASC "

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

            val event = EventItem(context)

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
            event.allDay = cur.getString(11)
            event.rrule = cur.getString(12)
            event.rdate = cur.getString(13)
            event.exrule = cur.getString(14)
            event.exdate = cur.getString(15)
            event.originalId = cur.getString(16)
            event.originalSyncId = cur.getString(17)
            event.originalInstanceTime = cur.getString(18)
            event.originalAllDay = cur.getString(19)
            event.accessLevel = cur.getString(20)
            event.availability = cur.getString(21)
            event.guestsCanModify = cur.getString(22)
            event.guestsCanInviteOthers = cur.getString(23)
            event.guestsCanSeeGuests = cur.getString(24)
            event.customAppPackage = cur.getString(25)
            event.customAppURI = cur.getString(26)
            event.uid2445 = cur.getString(27)
            event.displayColor = cur.getString(28)

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