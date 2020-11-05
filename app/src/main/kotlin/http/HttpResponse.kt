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

        this.responseHeaders.forEach { (key, value) ->  bufferedWriter.write("$key: $value${Constants.CRLF}") }
        bufferedWriter.write(Constants.CRLF)
        bufferedWriter.write(this.responseBody ?: "")
    }

}