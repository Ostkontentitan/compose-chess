package de.ostkontentitan.composechess.core

data class Square(
    val file: Int,
    val rank: Int,
    override val color: Color,
    var piece: Piece?
) : Colored
