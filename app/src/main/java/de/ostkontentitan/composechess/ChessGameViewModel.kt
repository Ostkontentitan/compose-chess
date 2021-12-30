package de.ostkontentitan.composechess

import androidx.lifecycle.ViewModel
import de.ostkontentitan.composechess.core.Board
import de.ostkontentitan.composechess.core.Square
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChessGameViewModel : ViewModel(), ChessInteractions {
    private val _gameState = MutableStateFlow(ChessInteractions.GameState(Board()))
    override val gameState: StateFlow<ChessInteractions.GameState> = _gameState

    override fun onSquareSelected(square: Square) {
        val old = _gameState.value
        _gameState.value = old.copy(selectedSquare = square)
    }
}
