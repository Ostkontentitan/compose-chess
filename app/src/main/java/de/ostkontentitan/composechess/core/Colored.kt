package de.ostkontentitan.composechess.core

interface Colored {
    val color: Color
    fun isWhite() = color == Color.WHITE
    fun isBlack() = color == Color.BLACK
}
