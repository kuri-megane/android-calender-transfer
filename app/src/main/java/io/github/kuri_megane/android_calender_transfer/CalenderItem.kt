package io.github.kuri_megane.android_calender_transfer

class CalenderItem : ItemInterface {

    var id: String = ""
    var name: String = ""

    override fun getValueArray(): Array<String?> {
        return arrayOf(
            id,
            name
        )
    }
}