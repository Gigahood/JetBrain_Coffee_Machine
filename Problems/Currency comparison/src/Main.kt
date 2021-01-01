import java.util.*

fun main(args: Array<String>) {
    // put your code here

    val scanner = Scanner(System.`in`)

    val country1 = scanner.next()
    val country2 = scanner.next()

    if (COUNTRY.findByName(country1).currency == COUNTRY.findByName(country2).currency) {
        print(true)
    } else {
        print(false)
    }
}

enum class COUNTRY(val names: String, val currency: String) {
    GERMANY("Germany", "Euro"),
    MALI("Mali", "CFA franc"),
    DOMINICA("Dominica", "Eastern Caribbean dollar"),
    CANADA("Canada", "Canadian dollar"),
    SPAIN("Spain", "Euro"),
    AUSTRALIA("Australia", "Australian dollar"),
    BRAZIL("Brazil", "Brazilian real"),
    SENEGAL("Senegal", "CFA franc"),
    FRANCE("France", "Euro"),
    GRENADA("Grenada", "Eastern Caribbean dollar"),
    KIRIBATI("Kiribati", "Australian dollar"),
    NULL("", "");

    companion object {
        fun findByName(name: String): COUNTRY {
            for (enum in COUNTRY.values()) {
                if (name == enum.names) return enum
            }
            return NULL
        }
    }
}