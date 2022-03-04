import java.util.Collections

class PerformedCommandStorage {
    private val actions = mutableListOf<String>()
    private val numbers = mutableListOf<Int>()
    private val swaps = mutableListOf<Pair<Int, Int>>()

    fun addHead(element: Int) {
        numbers.add(0, element)
        actions.add("ADD_HEAD")
    }

    fun addTail(element: Int) {
        numbers.add(element)
        actions.add("ADD_TAIL")
    }

    fun swapNumbers(firstIndex: Int, secondIndex: Int) {
        try {
            Collections.swap(numbers, firstIndex, secondIndex)
            swaps.add(Pair(firstIndex, secondIndex))
            actions.add("SWAP")
        } catch (e: ArrayIndexOutOfBoundsException) {
            println("Команда SWAP некорректна: одного из указанных при вызове индексов не существует. ${e.message}")
        }
    }

    fun cancelLastAction() {
        if (actions.isEmpty()) {
            println("Список команд пуст, отменять нечего")
            return
        }

        when (actions.removeLast()) {
            "ADD_HEAD" -> numbers.removeFirst()
            "ADD_TAIL" -> numbers.removeLast()
            else -> Collections.swap(numbers, swaps.removeLast().first, swaps.removeLast().second)
        }
    }

    fun printNumbers() {
        println(numbers.joinToString(separator = " "))
    }

    fun printActions() {
        println(actions.joinToString(separator = " "))
    }
}

val scan = java.util.Scanner(System.`in`)
fun main() {
    val storage = PerformedCommandStorage()
    println("Вводите команды по одной вместе с их аргументами. Если хотите завершить, введите END")
    println("Доступные команды: ADD_HEAD x, ADD_TAIL x, SWAP i j, CANCEL")

    var command = ""
    while (command.uppercase() != "END") {
        print(">>> ")
        command = scan.next()

        when (command.uppercase()) {
            "ADD_HEAD" -> storage.addHead(scan.nextInt())
            "ADD_TAIL" -> storage.addTail(scan.nextInt())
            "SWAP" -> storage.swapNumbers(scan.nextInt(), scan.nextInt())
            "CANCEL" -> storage.cancelLastAction()
            "END" -> break
            else -> println("Введённой команды не существует")
        }
    }

    storage.printNumbers()
    storage.printActions()
}
