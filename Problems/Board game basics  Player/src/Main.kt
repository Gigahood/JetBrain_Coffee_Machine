class Player(val id: Int, val name: String, val hp: Int) {
    companion object {
        var randomNumber = 0

        fun create(name: String): Player {
            randomNumber++
            return Player(randomNumber, name, 1000)
        }
    }
}