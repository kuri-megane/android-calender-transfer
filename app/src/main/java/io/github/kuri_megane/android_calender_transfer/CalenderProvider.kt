package io.github.kuri_megane.android_calender_transfer

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log

class CalenderProvider {

    fun getCalenderList(context: Context): MutableList<ItemInterface> {
        val uri: Uri = CalendarContract.Calendars.CONTENT_URI
        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null

        // SQLクエリ用 取得リスト
        val calenderListProjection = CalenderItemToQuery().getValueArray()

        // クエリを発行してカーソルを取得する
        val cr: ContentResolver = context.contentResolver
        val cur: Cursor = cr.query(uri, calenderListProjection, selection, selectionArgs, sortOrder)!!

        // カレンダーの総数取得
        val calContentValue = cur.count
        Log.d("リスト", "総数: $calContentValue")

        // カレンダー情報を保存するリスト
        val calenderList: MutableList<ItemInterface> = mutableListOf()

        // 各プロパティの取得
        while (cur.moveToNext()) {

            val calender = CalenderItem()

            // カーソルから各プロパティを取得する
            calender.id = cur.getString(0)
            calender.name = cur.getString(1)

            // ログ出力 (Body)
            val content = calender.getCsvRecord()
            Log.d("リスト", content)

            // リストに保存
            calenderList.add(calender as ItemInterface)
        }

        cur.close()
        return calenderList
    }
}