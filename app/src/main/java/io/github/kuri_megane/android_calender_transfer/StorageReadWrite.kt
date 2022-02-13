package io.github.kuri_megane.android_calender_transfer

import android.content.Context
import android.os.Environment
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets


class StorageReadWrite(context: Context, fileName : String) {
    private var file: File? = null
    private val stringBuffer: StringBuffer? = null

    // ファイルのパス設定とヘッダ作成
    init {
        val path: File? = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        file = File(path, fileName)
        // clearFile()
    }

    // ファイルとバッファのクリア
    private fun clearFile() {
        writeFile("", false)

        // TODO: 2度目以降は落ちる
        stringBuffer!!.setLength(0)
    }

    // ヘッダ作成
    fun writeHeader(header: ItemInterface) {
        writeFile(header.getCsvRecord(), true)
    }

    // レコード記録
    fun writeRecords(records: MutableList<ItemInterface>) {
        for (r in records) {
            writeFile(r.getCsvRecord(), true)
        }
    }

    // ファイルを保存
    private fun writeFile(record: String?, mode: Boolean) {
        if (isExternalStorageWritable()) {
            try {
                FileOutputStream(file, mode).use { fileOutputStream ->
                    OutputStreamWriter(
                        fileOutputStream,
                        StandardCharsets.UTF_8
                    ).use { outputStreamWriter ->
                        BufferedWriter(outputStreamWriter).use { bw ->
                            bw.write(record)
                            bw.flush()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /* Checks if external storage is available for read and write */
    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }
}