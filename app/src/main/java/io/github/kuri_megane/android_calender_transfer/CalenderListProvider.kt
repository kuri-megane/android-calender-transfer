package io.github.kuri_megane.android_calender_transfer

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log

// プロジェクション配列のインデックス。
// パフォーマンス向上のために、動的に取得せずに、静的に定義しておく。
private const val CALENDAR_PROJECTION_IDX_ID = 0
private const val CALENDAR_PROJECTION_IDX_NAME = 1


class CalenderListProvider {

    fun getCalenderList(context: Context): MutableList<Map<Long, String>> {
        val uri: Uri = CalendarContract.Calendars.CONTENT_URI
        val projection: Array<String> = CalenderListItem().getList()
        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null


        // クエリを発行してカーソルを取得する
        val cr: ContentResolver = context.contentResolver
        val cur: Cursor = cr.query(uri, projection, selection, selectionArgs, sortOrder)!!

        // カレンダーの総数取得
        val calContentValue = cur.count
        Log.d("リスト", "総数: $calContentValue")

        // カレンダー情報を保存するリスト
        val calenderKeyList : MutableList<Map<Long, String>> = mutableListOf()

        // 各プロパティの取得
        while (cur.moveToNext()) {

            // カーソルから各プロパティを取得する
            val id = cur.getLong(CALENDAR_PROJECTION_IDX_ID)
            val name = cur.getString(CALENDAR_PROJECTION_IDX_NAME)

            // ログ出力 (Body)
            val sbBody = java.lang.StringBuilder()
            sbBody.append(id).append(',').append(name)
            Log.d("リスト", sbBody.toString())

            // リストに保存
            val calender: Map<Long, String> = mapOf(id to name)
            calenderKeyList.add(calender)
        }

        cur.close()
        return calenderKeyList
    }
}