fun main() {
    val input = readLine()!!
    // write code here

    if (input.isNullOrEmpty()) {

    } else {
        when (input.first()) {
            'i' -> {
                println(((input.drop(1)).toInt() + 1).toString())
            }

            's' -> {
                println((input.drop(1)).reversed())
            }

            else -> {

            }
        }
    }


}