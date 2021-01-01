class Block(val color: String) {
    object DimProperties {
        var length = 0
        var width = 0

        fun blocksInBox(length: Int = 0, width: Int = 0): Int {
            return ((length / DimProperties.length) * (width / DimProperties.width))
        }
    }
}