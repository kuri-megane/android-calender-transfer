package io.github.kuri_megane.android_calender_transfer

import android.provider.CalendarContract

class EventItemToQuery : ItemInterface{

    /**
     *  アプリと同期アダプタのどちらからも書き込み可能
     */

    // イベントが属するカレンダーの_ID
    private var calenderId: String? =  CalendarContract.Events.CALENDAR_ID

    // イベントの主催者(所有者)のメールアドレス。
    var organizer: String = CalendarContract.Events.ORGANIZER

    // イベントのタイトル
    var title: String = CalendarContract.Events.TITLE

    // イベントの開催場所
    var eventLocation: String = CalendarContract.Events.EVENT_LOCATION

    // イベントの説明
    var description: String = CalendarContract.Events.DESCRIPTION

    // イベントの表示色
    var eventColor: String = CalendarContract.Events.EVENT_COLOR

    // エポックからの UTC ミリ秒単位で表現されたイベント開始時刻
    var dtstart: String = CalendarContract.Events.DTSTART

    // エポックからの UTC ミリ秒単位で表現されたイベント終了時刻
    var dtend: String = CalendarContract.Events.DTEND

    // イベントのタイムゾーン
    var eventTimezone: String = CalendarContract.Events.EVENT_TIMEZONE

    // イベントの終了時刻のタイムゾーン
    var eventEndTimezone: String = CalendarContract.Events.EVENT_END_TIMEZONE

    // RFC5545 形式でのイベントの期間
    var duration: String = CalendarContract.Events.DURATION

    // 値 1 はそのイベントがローカルのタイムゾーンで定義された終日を占めることを示します
    // 値 0 は1 日のどこかで始まって終わる定期のイベントであることを示します
    var allDay: String = CalendarContract.Events.DURATION

    // イベント形式の繰り返し発生ルール
    // たとえば "FREQ=WEEKLY;COUNT=10;WKST=SU" のようになります
    var rrule: String = CalendarContract.Events.RRULE

    // イベントの繰り返し発生日
    var rdate: String = CalendarContract.Events.RDATE

    // イベントの繰り返し例外ルール
    var exrule: String = CalendarContract.Events.EXRULE

    // イベントの繰り返し例外の日付
    var exdate: String = CalendarContract.Events.EXDATE

    // The Events#_ID of the original recurring event
    // for which this event is an exception.
    var originalId: String = CalendarContract.Events.ORIGINAL_ID

    // The _sync_id of the original recurring event
    // for which this event is an exception.
    var originalSyncId: String = CalendarContract.Events.ORIGINAL_SYNC_ID

    // The original instance time of the recurring event
    // for which this event is an exception.
    var originalInstanceTime: String = CalendarContract.Events.ORIGINAL_INSTANCE_TIME

    // The allDay status (true or false) of the original recurring event
    // for which this event is an exception.
    var originalAllDay: String = CalendarContract.Events.ORIGINAL_ALL_DAY

    // Defines how the event shows up for others when the calendar is shared.
    var accessLevel: String = CalendarContract.Events.ACCESS_LEVEL

    // このイベントの時間を予定が入っている時間とみなすか調整可能な空き時間とみなすか
    var availability: String = CalendarContract.Events.AVAILABILITY

    // ゲストがイベントを変更できるかどうか
    var guestsCanModify: String = CalendarContract.Events.GUESTS_CAN_MODIFY

    // ゲストが他のゲストを招待できるかどうか
    var guestsCanInviteOthers: String = CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS

    // ゲストが他の参加者のリストを参照できるかどうか
    var guestsCanSeeGuests: String = CalendarContract.Events.GUESTS_CAN_SEE_GUESTS

    // The package name of the custom app
    // that can provide a richer experience for the event.
    var customAppPackage: String = CalendarContract.Events.CUSTOM_APP_PACKAGE

    // The URI used by the custom app for the event.
    var customAppURI: String = CalendarContract.Events.CUSTOM_APP_URI

    // The UID for events added from the RFC 2445 iCalendar format.
    var uid2445: String = CalendarContract.Events.UID_2445

    // イベントの実際の表示色 EVENT_COLORが未設定の場合にはCALENDAR_COLORが使用される
    var displayColor: String = CalendarContract.Events.DISPLAY_COLOR

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
}