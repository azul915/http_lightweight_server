package http

import java.io.*
import java.lang.StringBuilder
import java.nio.*
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

class HttpResponse(status: Status) {

    private val responseHeaders = mutableMapOf<String, String>()
    private lateinit var responseBody: String
    private lateinit var responseBodyFile: File

    fun addResponseHeader(headerKey: String, headerValue: String) {
        this.responseHeaders[headerKey] = headerValue
    }

    fun setResponseBody(body: String) {
        this.responseBody = body
    }

    fun setResponseBodyFile(file: File) {
        this.responseBodyFile = file
    }

    fun writeOut(bufferedWriter: BufferedWriter) {

        writeResponseHeader(bufferedWriter)
        bufferedWriter.write(Constants.CRLF)

        writeResponseBody(bufferedWriter)
        bufferedWriter.flush()

    }

    fun writeFileOut(bufferedWriter: BufferedWriter) {

        writeResponseHeader(bufferedWriter)
        bufferedWriter.write(Constants.CRLF)

        val file = this.responseBodyFile
        val fileStr = BufferedReader(FileReader(file)).use { br -> br.readText() }
        this.setResponseBody(fileStr)

        writeResponseBody(bufferedWriter)
        bufferedWriter.flush()

    }

    private fun writeResponseHeader(bufferedWriter: BufferedWriter) {

        this.responseHeaders.forEach { (key, value) -> bufferedWriter.write("$key: $value${Constants.CRLF}") }
    }

    private fun writeResponseBody(bufferedWriter: BufferedWriter) {

        bufferedWriter.write(this.responseBody ?: "")

    }

}
