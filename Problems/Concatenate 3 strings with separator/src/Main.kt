import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val input1 = scanner.nextLine()
    val input2 = scanner.nextLine()
    val input3 = scanner.nextLine()
    val input4 = if (scanner.hasNext()) scanner.nextLine() else " "

    if (input4 == "NO SEPARATOR") {
        println("$input1 $input2 $input3")
    } else {
        println(input1 + input4 + input2 + input4 + input3)
    }
}