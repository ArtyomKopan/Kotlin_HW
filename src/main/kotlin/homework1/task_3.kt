package homework.one

enum class Command {
    ADD_HEAD,
    ADD_TAIL,
    MOVE,
    CANCEL,
    END,
    ERROR
}

fun commandExecute(): List<Int> {
    val storage = PerformedCommandStorage()
    val scan = java.util.Scanner(System.`in`)

    var command: Command
    while (true) {
        print(">>> ")
        try {
            command = Command.valueOf(scan.next().uppercase())
        } catch (e: IllegalArgumentException) {
            command = Command.ERROR
            println("Ошибка: введённой команды не существует (${e.message})")
        }
        when (command) {
            Command.ADD_HEAD -> storage.addAction(AddHead(scan.nextInt()))
            Command.ADD_TAIL -> storage.addAction(AddTail(scan.nextInt()))
            Command.MOVE -> {
                val from = scan.nextInt()
                val to = scan.nextInt()
                try {
                    storage.addAction(Move(from, to))
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
            }
            Command.CANCEL -> {
                try {
                    storage.cancelLastAction()
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
            }
            Command.END -> break
        }
    }
    return storage.publicNumbers
}

fun main() {
    println("Вводите команды по одной вместе с их аргументами. Если хотите завершить, введите END")
    println("Доступные команды: ADD_HEAD x, ADD_TAIL x, MOVE i j, CANCEL")

    val resultList = commandExecute()

    println(resultList.joinToString(separator = " "))
}
