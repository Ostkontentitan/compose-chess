package de.ostkontentitan.composechess.core

data class Board(
    val ranks: MutableList<Rank> = mutableListOf()
) {

    init {
        setupForNewGame()
    }
}

typealias Rank = List<Square>

fun Board.setupForNewGame() {
    ranks.addAll(
        listOf(
            baseRankPiecesFor(7, Color.BLACK, Color.WHITE),
            pawnsForColor(6, Color.WHITE, Color.WHITE),
            emptyRank(5, Color.BLACK),
            emptyRank(4, Color.WHITE),
            emptyRank(3, Color.BLACK),
            emptyRank(2, Color.WHITE),
            pawnsForColor(1, Color.BLACK, Color.BLACK),
            baseRankPiecesFor(0, Color.WHITE, Color.BLACK)
        )
    )
}

fun baseRankPiecesFor(rank: Int, firstSquareColor: Color, pieceColor: Color) = listOf(
    Rook(pieceColor),
    Knight(pieceColor),
    Bishop(pieceColor),
    Queen(pieceColor),
    King(pieceColor),
    Bishop(pieceColor),
    Knight(pieceColor),
    Rook(pieceColor)
).mapIndexed { file, piece -> Square(file, rank, colorForIndex(file, firstSquareColor), piece) }

fun pawnsForColor(rank: Int, firstSquareColor: Color, pieceColor: Color) =
    (0..7).map { Pawn(pieceColor) }
        .mapIndexed { file, piece -> Square(file, rank, colorForIndex(file, firstSquareColor), piece) }

fun emptyRank(rank: Int, firstSquareColor: Color) =
    (0..7).mapIndexed { file, _ -> Square(file, rank, colorForIndex(file, firstSquareColor), null) }

fun colorForIndex(index: Int, baseColor: Color) =
    if (index % 2 == 0) baseColor else baseColor.opposite()
