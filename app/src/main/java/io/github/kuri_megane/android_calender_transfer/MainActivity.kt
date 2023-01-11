package io.github.kuri_megane.android_calender_transfer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: カレンダーのパーミッション取得を実装する

        // カレンダーリスト出力用CSVの準備
        val calenderWriter = StorageReadWrite(applicationContext, "calender-list.csv")
        calenderWriter.writeHeader(EventItemToQuery() as ItemInterface)

        // カレンダーリストの取得
        val calenderListProvider = CalenderProvider()
        val calenderList = calenderListProvider.getCalenderList(applicationContext)
        calenderWriter.writeRecords(calenderList)

        // イベントリスト出力用CSVの準備
        val eventWriter = StorageReadWrite(applicationContext, "event.csv")
        eventWriter.writeHeader(EventItemToQuery() as ItemInterface)

        // イベントリストの取得
        val eventListProvider = EventReader()
        val eventLists = eventListProvider.getEvent(applicationContext)
        eventWriter.writeEventRecords(eventLists)

        // イベントの登録
        val register = EventRegister(applicationContext)
        register.registerEvent(eventLists)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}