package http

import java.io.BufferedWriter

class HttpResponse(status: Status) {
    private val responseHeaders = mutableMapOf<String, String>()
    private var responseBody: String = ""

    fun addResponseHeader(headerKey: String, headerValue: String) {
        this.responseHeaders[headerKey] = headerValue
    }

    fun setResponseBody(body: String) {
        this.responseBody = body
    }

    fun writeOut(bufferedWriter: BufferedWriter) {
        val CRLF = "\r\n"
        this.responseHeaders.forEach { (key, value) ->  bufferedWriter.write("$key: $value$CRLF") }
        bufferedWriter.write(CRLF)
        bufferedWriter.write(this.responseBody ?: "")
    }

}