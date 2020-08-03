package http

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class HttpRequest(inputStream: InputStream) {

    val CRLF = "\r\n"

    private val requestHeaderText: String
    private val requestBodyText: String
    init {
        val br = BufferedReader(InputStreamReader(inputStream))
        requestHeaderText = readRequestHeader(br)
        requestBodyText = readRequestBody(br)
    }

    private fun readRequestHeader(bufferedReader: BufferedReader): String {

        // リクエストヘッダ用ビルダー
        val header = StringBuilder()

        // 行単位で読み込んでビルダーに追加する
        // 空行で読込終了
        for (line in bufferedReader.lines()) {
            if (line.isNullOrEmpty()) break
            header.append("$line$CRLF")
        }

        return header.toString()
    }

    private fun readRequestBody(bufferedReader: BufferedReader): String {
        // 読み込んだリクエストヘッダーを行単位に分割
        val headerLineArray = requestHeaderText.split(CRLF)

        // リクエストヘッダーからContent-Lengthを取得する
        val contentLengthLine = headerLineArray.firstOrNull { it.startsWith("Content-Length") } ?: "Content-Length : 0"
        val contentLength = contentLengthLine.takeAfterColon().toInt()

        if (contentLength <= 0) return ""

        // リクエストボディ取得
        val c = CharArray(contentLength)
        bufferedReader.read(c)
        return String(c)
    }
}