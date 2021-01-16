package model

enum class FileType(
    val displayName: String,
    val extension: String
) {

    TEXT("Text", "txt"),
    JAVA("Java","java");
    override fun toString() = displayName
}