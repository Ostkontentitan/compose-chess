package de.ostkontentitan.composechess.core

data class Square(override val color: Color, var piece: Piece?) : Colored {
    override fun toString(): String {
        return piece?.stringRep ?: "O"
    }
}
