package io.github.kuri_megane.android_calender_transfer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // カレンダーリストの取得
        val calenderListProvider = CalenderListProvider()
        val calenderList = calenderListProvider.getCalenderList(applicationContext)

        // イベントリストの取得
        val eventListProvider = CalenderEventProvider()
        val eventList = eventListProvider.getEvent(applicationContext)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}