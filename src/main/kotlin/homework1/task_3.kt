package homework.one

fun commandProcess(): MutableList<Int> {
    val storage = PerformedCommandStorage()

    val scan = java.util.Scanner(System.`in`)

    var command = ""
    while (command.uppercase() != "END") {
        print(">>> ")
        command = scan.next()

        when (command.uppercase()) {
            "ADD_HEAD" -> storage.addAction(AddHead(scan.nextInt()))
            "ADD_TAIL" -> storage.addAction(AddTail(scan.nextInt()))
            "MOVE" -> {
                val from = scan.nextInt()
                val to = scan.nextInt()
                try {
                    storage.addAction(Move(from, to))
                } catch (e: IndexOutOfBoundsException) {
                    println(e.message)
                }
            }
            "CANCEL" -> {
                try {
                    storage.cancelLastAction()
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
            }
            "END" -> break
            else -> println("Введённой команды не существует")
        }
    }
    return storage.numbers
}

fun main() {
    println("Вводите команды по одной вместе с их аргументами. Если хотите завершить, введите END")
    println("Доступные команды: ADD_HEAD x, ADD_TAIL x, MOVE i j, CANCEL")

    val resultList = commandProcess()

    println(resultList.joinToString(separator = " "))
}
