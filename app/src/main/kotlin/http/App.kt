/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package http

import java.io.*
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket

fun main(args: Array<String>) {

    // on Docker
    //val HOST = "0.0.0.0"
    val HOST = "localhost"
    val PORT = 8080
    val CRLF = "\r\n"

    println("start >>>")

    // Socket生成、IPとPort指定、SO_REUSEADDRオプション有効化
    val serverSocket = ServerSocket()
    serverSocket.bind(InetSocketAddress(HOST, PORT))
    serverSocket.reuseAddress = true
    println("listening on... ${serverSocket.localSocketAddress}")

    try {
        // 受信
        val socket = serverSocket.accept()
        val ins = socket.getInputStream()
        val bw = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        val request = HttpRequest(ins)

        // リクエストヘッダー出力
        println(request.headerText())

        // リクエストボディー出力
        println(request.bodyText())

        // レスポンス
        val response = HttpResponse(Status.OK)
        response.addResponseHeader("Content-Type", ContentType.TEXT_HTML.toString())
        response.setResponseBody("<h1>Test Kotlin!!</h1>")
        response.writeOut(bw)
        bw.flush()


    } catch (e: IOException) {
        println("CAUSE: ${e.cause}, MESSAGE: ${e.message}")
    }

    println("<<< end")

}

