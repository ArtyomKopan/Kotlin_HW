package homework1

fun commandProcessing(): MutableList<Int> {
    val storage = PerformedCommandStorage()

    val scan = java.util.Scanner(System.`in`)

    var command = ""
    while (command.uppercase() != "END") {
        print(">>> ")
        command = scan.next()

        when (command.uppercase()) {
            "ADD_HEAD" -> storage.addAction(AddHead(), listOf(scan.nextInt()))
            "ADD_TAIL" -> storage.addAction(AddTail(), listOf(scan.nextInt()))
            "MOVE" -> {
                val firstIndex = scan.nextInt()
                val secondIndex = scan.nextInt()
                if (firstIndex < 0 || secondIndex < 0) {
                    println("Команда MOVE некорректна: один из индексов отрицателен!")
                } else if (firstIndex >= storage.numbers.size || secondIndex >= storage.numbers.size) {
                    println("Команда MOVE некорректна: один из индексов больше длины списка!")
                } else {
                    storage.addAction(Move(), listOf(firstIndex, secondIndex))
                }
            }
            "CANCEL" -> storage.cancelLastAction()
            "END" -> break
            else -> println("Введённой команды не существует")
        }
    }
    return storage.numbers
}

fun main() {
    println("Вводите команды по одной вместе с их аргументами. Если хотите завершить, введите END")
    println("Доступные команды: ADD_HEAD x, ADD_TAIL x, MOVE i j, CANCEL")

    val resultList = commandProcessing()

    println(resultList.joinToString(separator = " "))
}
