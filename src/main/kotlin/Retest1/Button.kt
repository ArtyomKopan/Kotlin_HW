data class Button(
    val id: Int,
    val name: String
) {
    companion object {
        fun getTempButton() = Button(1, "Лучшие цитаты")
    }
}
