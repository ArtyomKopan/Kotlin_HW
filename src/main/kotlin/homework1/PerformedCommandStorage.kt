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
        Collections.swap(numbers, firstIndex, secondIndex)
        swaps.add(Pair(firstIndex, secondIndex))
        actions.add("SWAP")
    }

    fun cancelLastAction() {
        if (actions.isEmpty())
            return

        when (actions.removeLast()) {
            "ADD_HEAD" -> numbers.removeFirst()
            "ADD_TAIL" -> numbers.removeLast()
            else -> Collections.swap(numbers, swaps.removeLast().first, swaps.removeLast().second)
        }
    }

    fun getNumbers(): MutableList<Int> = numbers
}

val scan = java.util.Scanner(System.`in`)

fun commandProcessing(commandBuffer: MutableList<Triple<String, Int?, Int?>>): MutableList<Int> {
    val storage = PerformedCommandStorage()

    for (command in commandBuffer) {
        when (command.first) {
            "ADD_HEAD" -> storage.addHead(command.second!!)
            "ADD_TAIL" -> storage.addTail(command.second!!)
            "SWAP" -> storage.swapNumbers(command.second!!, command.third!!)
            "CANCEL" -> storage.cancelLastAction()
        }
    }

    return storage.getNumbers()
}

fun commandInput(): MutableList<Triple<String, Int?, Int?>> {
    println("Вводите команды по одной вместе с их аргументами. Если хотите завершить, введите END")
    println("Доступные команды: ADD_HEAD x, ADD_TAIL x, SWAP i j, CANCEL")

    val commandBuffer = mutableListOf<Triple<String, Int?, Int?>>()
    var command = ""
    var commandsCount = 0
    var numberListLength = 0

    while (command.uppercase() != "END") {
        print(">>> ")
        command = scan.next()

        when (command.uppercase()) {
            "ADD_HEAD", "ADD_TAIL" -> {
                commandBuffer.add(Triple(command, scan.nextInt(), null))
                numberListLength++
                commandsCount++
            }
            "SWAP" -> {
                val firstIndex = scan.nextInt()
                val secondIndex = scan.nextInt()
                if (firstIndex >= numberListLength || secondIndex >= numberListLength) {
                    println("Команда SWAP некорректна: одного из указанных при вызове индексов не существует")
                } else {
                    commandsCount++
                    commandBuffer.add(Triple(command, firstIndex, secondIndex))
                }
            }
            "CANCEL" -> {
                commandBuffer.add(Triple(command, null, null))
                if (commandsCount == 0)
                    println("Список команд пуст, отменять нечего")
                else
                    commandsCount--
            }
            "END" -> break
            else -> println("Введённой команды не существует")
        }
    }
    return commandBuffer
}

fun main() {
    val commandBuffer = commandInput()
    val resultNumberList = commandProcessing(commandBuffer)
    println(resultNumberList.joinToString(separator = " "))
}
