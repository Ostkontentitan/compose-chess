package de.ostkontentitan.composechess.core

data class Board(
    val ranks: MutableList<Rank> = mutableListOf()
) {

    val stringRep: String
        get() = ranks.reversed()
            .joinToString("\n") {
                it.joinToString("") { square -> square.toString() }
            }
}

data class Square(val color: Color, var piece: Piece?) {
    override fun toString(): String {
        return piece?.stringRep ?: "O"
    }
}

typealias Rank = List<Square>

fun Board.setupForNewGame() {
    ranks.addAll(
        listOf(
            baseRankPiecesFor(Color.WHITE, Color.BLACK),
            pawnsForColor(Color.WHITE, Color.WHITE),
            emptyRank(Color.BLACK),
            emptyRank(Color.WHITE),
            emptyRank(Color.BLACK),
            emptyRank(Color.WHITE),
            pawnsForColor(Color.BLACK, Color.BLACK),
            baseRankPiecesFor(Color.BLACK, Color.WHITE)
        )
    )
}

fun baseRankPiecesFor(firstSquareColor: Color, pieceColor: Color) = listOf(
    Rook(pieceColor),
    Knight(pieceColor),
    Bishop(pieceColor),
    Queen(pieceColor),
    King(pieceColor),
    Bishop(pieceColor),
    Knight(pieceColor),
    Rook(pieceColor)
).mapIndexed { index, piece -> Square(colorForIndex(index, firstSquareColor), piece) }

fun pawnsForColor(firstSquareColor: Color, pieceColor: Color) =
    (0..7).map { Pawn(pieceColor) }
        .mapIndexed { index, piece -> Square(colorForIndex(index, firstSquareColor), piece) }

fun emptyRank(firstSquareColor: Color) =
    (0..7).mapIndexed { index, _ -> Square(colorForIndex(index, firstSquareColor), null) }

fun colorForIndex(index: Int, baseColor: Color) =
    if (index % 2 == 0) baseColor else baseColor.opposite()
