const val NUMBER_BEST_QUOTES = 1
const val NUMBER_NEW_QUOTES = 2
const val NUMBER_RANDOM_QUOTES = 3

fun fetchButtons(): List<Button> {
    return mutableListOf(
        Button(NUMBER_BEST_QUOTES, "Лучшие цитаты"),
        Button(NUMBER_NEW_QUOTES, "Новые цитаты"),
        Button(NUMBER_RANDOM_QUOTES, "Случайная цитата")
    )
}
