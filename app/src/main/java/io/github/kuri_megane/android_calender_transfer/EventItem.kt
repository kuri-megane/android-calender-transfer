package io.github.kuri_megane.android_calender_transfer

import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import java.util.*

open class EventItem(private var context: Context) : ItemInterface {

    // イベントが属するカレンダーの_ID
    var calenderId: String? = null

    // イベントの主催者(所有者)のメールアドレス。
    var organizer: String? = null

    // イベントのタイトル
    var title: String? = null

    // イベントの開催場所
    var eventLocation: String? = null

    // イベントの説明
    var description: String? = "空っぽ"
//        get() {
//            return "\"" + field + "\""
//        }

    // イベントの表示色
    var eventColor: String? = null

    // エポックからの UTC ミリ秒単位で表現されたイベント開始時刻
    var dtstart: String? = null

    // エポックからの UTC ミリ秒単位で表現されたイベント終了時刻
    var dtend: String? = null
        get() {
            if (field == null && this.duration == null) {
                Log.w("予定", "DT_END 値なし")
                return this.dtstart
            }
            return field
        }

    // イベントのタイムゾーン
    var eventTimezone: String? = null
        get() {
            if (field == null) {
                Log.w("予定", "Timezone 値なし")
                return "Asia/Tokyo"
            }
            return field
        }

    // イベントの終了時刻のタイムゾーン
    var eventEndTimezone: String? = null

    // RFC5545 形式でのイベントの期間
    var duration: String? = null

    // 値 1 はそのイベントがローカルのタイムゾーンで定義された終日を占めることを示します
    // 値 0 は1 日のどこかで始まって終わる定期のイベントであることを示します
    var allDay: String? = null

    // イベント形式の繰り返し発生ルール
    // たとえば "FREQ=WEEKLY;COUNT=10;WKST=SU" のようになります
    var rrule: String? = null

    // イベントの繰り返し発生日
    var rdate: String? = null

    // イベントの繰り返し例外ルール
    var exrule: String? = null

    // イベントの繰り返し例外の日付
    var exdate: String? = null

    // The Events#_ID of the original recurring event
    // for which this event is an exception.
    var originalId: String? = null

    // The _sync_id of the original recurring event
    // for which this event is an exception.
    var originalSyncId: String? = null

    // The original instance time of the recurring event
    // for which this event is an exception.
    var originalInstanceTime: String? = null

    // The allDay status (true or false) of the original recurring event
    // for which this event is an exception.
    var originalAllDay: String? = null

    // Defines how the event shows up for others when the calendar is shared.
    var accessLevel: String? = null

    // このイベントの時間を予定が入っている時間とみなすか調整可能な空き時間とみなすか
    var availability: String? = null

    // ゲストがイベントを変更できるかどうか
    var guestsCanModify: String? = null

    // ゲストが他のゲストを招待できるかどうか
    var guestsCanInviteOthers: String? = null

    // ゲストが他の参加者のリストを参照できるかどうか
    var guestsCanSeeGuests: String? = null

    // The package name of the custom app
    // that can provide a richer experience for the event.
    var customAppPackage: String? = null

    // The URI used by the custom app for the event.
    var customAppURI: String? = null

    // The UID for events added from the RFC 2445 iCalendar format.
    var uid2445: String? = null

    // イベントの実際の表示色 EVENT_COLORが未設定の場合にはCALENDAR_COLORが使用される
    var displayColor: String? = null

    private val dtstartString: String
        get() {
            return getDateTimeText(context, this.eventTimezone, this.dtstart)
        }

    private val dtendString: String
        get() {
            return getDateTimeText(context, this.eventTimezone, this.dtend)
        }

    override fun getValueArray(): Array<String?> {
        return arrayOf(
            this.calenderId,
            this.organizer,
            this.title,
            this.eventLocation,
            this.description,
            this.eventColor,
            this.dtstart,
            this.dtend,
            this.eventTimezone,
            this.eventEndTimezone,
            this.duration,
            this.allDay,
            this.rrule,
            this.rdate,
            this.exrule,
            this.exdate,
            this.originalId,
            this.originalSyncId,
            this.originalInstanceTime,
            this.originalAllDay,
            this.accessLevel,
            this.availability,
            this.guestsCanModify,
            this.guestsCanInviteOthers,
            this.guestsCanSeeGuests,
            this.customAppPackage,
            this.customAppURI,
            this.uid2445,
            this.displayColor,
        )
    }

    override fun getCsvRecord(): String {
        val add = "," + this.dtstartString + "," + this.dtendString
        return this.getValueArray().joinToString(separator = ",") + add + "\n"
    }

    /**
     *  UNIX time (1970/01/01 00:00:00からの経過時刻) を文字列化するためのメソッド
     */
    private fun getDateTimeText(
        context: Context,
        timeZone: String?,
        dateTimeInMillis: String?
    ): String {
        if (dateTimeInMillis == null) {
            return "時刻空"
        }
        if (timeZone == null) {
            return "タイムゾーン空"
        }
        val calendar: Calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone(timeZone))
        calendar.timeInMillis = dateTimeInMillis.toLong()
        return DateUtils.formatDateRange(
            context,
            Formatter(),
            calendar.timeInMillis,
            calendar.timeInMillis,
            DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR,
            timeZone
        ).toString()
    }
}