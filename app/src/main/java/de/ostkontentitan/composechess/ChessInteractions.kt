package de.ostkontentitan.composechess

import de.ostkontentitan.composechess.core.Board
import de.ostkontentitan.composechess.core.Square
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ChessInteractions {
    val gameState: StateFlow<GameState>

    fun onSquareSelected(square: Square)
    data class GameState(val board: Board, val selectedSquare: Square? = null)

    object Preview : ChessInteractions {
        override val gameState: StateFlow<GameState> = MutableStateFlow(GameState(Board()))
        override fun onSquareSelected(square: Square) = Unit
    }
}
