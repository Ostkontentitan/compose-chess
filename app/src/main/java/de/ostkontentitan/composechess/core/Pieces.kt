package de.ostkontentitan.composechess.core

sealed class Piece : Colored {
    abstract override val color: Color

    val stringRep: String
        get() = when (this) {
            is Pawn -> "p"
            is Bishop -> "B"
            is King -> "K"
            is Knight -> "N"
            is Queen -> "Q"
            is Rook -> "R"
        }
}

data class Pawn(override val color: Color) : Piece()
data class King(override val color: Color) : Piece()
data class Queen(override val color: Color) : Piece()
data class Rook(override val color: Color) : Piece()
data class Knight(override val color: Color) : Piece()
data class Bishop(override val color: Color) : Piece()

enum class Color {
    WHITE, BLACK;

    fun opposite() = when (this) {
        WHITE -> BLACK
        BLACK -> WHITE
    }
}
