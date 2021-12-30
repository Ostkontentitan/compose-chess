package de.ostkontentitan.composechess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import de.ostkontentitan.composechess.core.Board
import de.ostkontentitan.composechess.core.Rank
import de.ostkontentitan.composechess.core.Square
import de.ostkontentitan.composechess.core.setupForNewGame
import de.ostkontentitan.composechess.ui.theme.ComposeChessTheme
import de.ostkontentitan.composechess.ui.theme.DarkSquare
import de.ostkontentitan.composechess.ui.theme.LightSquare

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ChessGameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ChessBoard(viewModel)
                }
            }
        }
    }
}

@Composable
fun ChessBoard(chessInteractions: ChessInteractions) {
    val state = chessInteractions.gameState.collectAsState()
    Column(
        modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val actual = state.value
        actual.board.ranks.reversed().forEach { rank ->
            Rank(rank = rank, actual.selectedSquare, chessInteractions)
        }
    }
}

@Composable
fun Rank(rank: Rank, selectedSquare: Square?, chessInteractions: ChessInteractions) {
    Row {
        rank.forEach { square ->
            Square(square = square, selectedSquare, chessInteractions)
        }
    }
}

@Composable
fun Square(square: Square, selectedSquare: Square?, chessInteractions: ChessInteractions) {
    val modifier = Modifier.clickable { chessInteractions.onSquareSelected(square) }
        .height(48.dp)
        .width(48.dp)
        .run {
            if (square == selectedSquare) {
                this.border(border = BorderStroke(8.dp, color = MaterialTheme.colors.primary))
            } else this
        }

    Surface(
        modifier = modifier,
        color = if (square.isWhite()) LightSquare else DarkSquare
    ) {
        val pieceOnSquare = square.piece
        if (pieceOnSquare != null) {
            Icon(
                painter = painterResource(id = pieceOnSquare.drawable),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeChessTheme {
        val board = Board()
        board.setupForNewGame()
        ChessBoard(ChessInteractions.Preview)
    }
}
