import java.util.*
import kotlin.math.pow

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val argument = scanner.nextLine()
    val number = scanner.nextInt()

    when (argument) {
        "amount" -> println((calculate(amount = number)).toInt())
        "percent" -> println((calculate(percent = number)).toInt())
        "years" -> println((calculate(years = number)).toInt())
    }
}

fun calculate(
    amount: Int = 1000,
    percent: Int = 5,
    years: Int = 10
): Double {
    return amount * ((1 + percent.toDouble() / 100).pow(years.toDouble()))
}