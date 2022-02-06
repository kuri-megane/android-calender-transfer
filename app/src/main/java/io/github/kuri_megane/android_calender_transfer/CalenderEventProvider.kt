package io.github.kuri_megane.android_calender_transfer

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.text.format.DateUtils
import android.util.Log
import java.util.*


class CalenderEventProvider {

    /**
     *  UNIX time (1970/01/01 00:00:00からの経過時刻) を文字列化するためのメソッド
     */
    private fun getDateTimeText(
        context: Context,
        timeZone: String,
        dateTimeInMillis: Long
    ): String? {
        val calendar: Calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone(timeZone))
        calendar.timeInMillis = dateTimeInMillis
        return DateUtils.formatDateRange(
            context,
            Formatter(),
            calendar.timeInMillis,
            calendar.timeInMillis,
            DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR,
            timeZone
        ).toString()
    }

    fun getEvent(context: Context): MutableList<Map<Long, String>> {
        // 検索対象のカレンダーの _ID
        val targetCalendarId: Long = 29

        // クエリ条件を設定する
        val uri: Uri = CalendarContract.Events.CONTENT_URI
        val projection: Array<String> = CalenderListItem().getList()
        val selection = "(" + CalendarContract.Events.CALENDAR_ID + " = ?)"
        val selectionArgs = arrayOf(targetCalendarId.toString())
        val sortOrder: String? = null

        val eventProjection = CalenderEventItem().getValue()

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
        val eventKeyList: MutableList<Map<Long, String>> = mutableListOf()

        // カーソルから各プロパティを取得する
        while (cur.moveToNext()) {

            // カーソルから各プロパティを取得する
            val id = cur.getLong(0)
            val calendarId = cur.getLong(1)
            val title = cur.getString(2)
            val description = cur.getString(3)

            // ログ出力 (Body)
            val sbBody = java.lang.StringBuilder()
            sbBody
                .append(id).append(',')
                .append(calendarId).append(',')
                .append(title).append(',')
                .append(description).append(',')
            val content = sbBody.toString()
            Log.d("予定", content)

            // リストに保存
            val event: Map<Long, String> = mapOf(id to content)
            eventKeyList.add(event)
        }

        cur.close()
        return eventKeyList
    }
}