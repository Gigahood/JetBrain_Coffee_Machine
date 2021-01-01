fun main(args: Array<String>) {
    val color = readLine()!!
    // put your code here

    println((RAINBOW.findByColor(color).ordinal) + 1)
}

enum class RAINBOW(val color: String) {
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    INDIGO("indigo"),
    VIOLET("violet"),
    NULL("");

    companion object {
        fun findByColor(color: String): RAINBOW {
            for (enum in RAINBOW.values()) {
                if (enum.color == color) {
                    return enum
                }
            }
            return NULL
        }
    }
}