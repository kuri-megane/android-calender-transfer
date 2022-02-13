package io.github.kuri_megane.android_calender_transfer

import android.content.Context
import android.text.format.DateUtils
import java.util.*

open class EventItem: ItemInterface {

    // イベントが属するカレンダーの_ID
    var calenderId: String? =  null

    // イベントの主催者(所有者)のメールアドレス。
    var organizer: String? = null

    // イベントのタイトル
    var title: String? = null

    // イベントの開催場所
    var eventLocation: String? = null

    // イベントの説明
    var description: String? = "空っぽ"
        get() {
            return "\"" + field + "\""
        }

    // イベントの表示色
    var eventColor: String? = null

    // エポックからの UTC ミリ秒単位で表現されたイベント開始時刻
    var dtstart: String? = null

    // エポックからの UTC ミリ秒単位で表現されたイベント終了時刻
    var dtend: String? = null

    // イベントのタイムゾーン
    var eventTimezone: String? = null

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
            this.dtend,
            this.eventTimezone,
            this.eventEndTimezone,
            this.duration,
        )
    }

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
}