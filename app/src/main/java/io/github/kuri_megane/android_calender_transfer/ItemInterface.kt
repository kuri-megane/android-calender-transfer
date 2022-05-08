package io.github.kuri_megane.android_calender_transfer

interface ItemInterface {
    fun getValueArray(): Array<String?>
    fun getCsvRecord(): String {
        return this.getValueArray().joinToString(separator = ",") + "\n"
    }
}