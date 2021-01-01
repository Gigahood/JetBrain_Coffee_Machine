import java.lang.Math.abs
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val a = scanner.nextLine()
    val b = scanner.nextLine()
    val c = if (b == "no limit") 60 else b.toInt()

    checkSpeed(a.toInt(), c)
}

fun checkSpeed(limit: Int = 60, currentSpeed: Int) {
    when (currentSpeed - limit) {
        in 0..Int.MAX_VALUE -> println("Within the limit")
        else -> println("Exceeds the limit by ${kotlin.math.abs(currentSpeed - limit)} kilometers per hour")
    }
}