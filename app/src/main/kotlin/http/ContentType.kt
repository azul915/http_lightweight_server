package http

enum class ContentType(private val contentType: String) {
    TEXT_HTML("text/html");

    override fun toString(): String {
        return this.contentType
    }
}