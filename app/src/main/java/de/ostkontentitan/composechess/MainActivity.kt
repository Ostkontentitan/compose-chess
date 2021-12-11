package de.ostkontentitan.composechess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.ostkontentitan.composechess.core.Board
import de.ostkontentitan.composechess.core.Rank
import de.ostkontentitan.composechess.core.Square
import de.ostkontentitan.composechess.core.setupForNewGame
import de.ostkontentitan.composechess.ui.theme.ComposeChessTheme
import de.ostkontentitan.composechess.ui.theme.DarkSquare
import de.ostkontentitan.composechess.ui.theme.LightSquare

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val board = Board()
                    board.setupForNewGame()
                    ChessBoard(board = board)
                }
            }
        }
    }
}

@Composable
fun ChessBoard(board: Board) {
    Column(
        modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        board.ranks.reversed().forEach { rank ->
            Rank(rank = rank)
        }
    }
}

@Composable
fun Rank(rank: Rank) {
    Row {
        rank.forEach { square ->
            Square(square = square)
        }
    }
}

@Composable
fun Square(square: Square) {
    Surface(
        modifier = Modifier
            .height(48.dp)
            .width(48.dp),
        color = if (square.isWhite()) LightSquare else DarkSquare
    ) {
        val pieceOnSquare = square.piece
        if (pieceOnSquare != null) {
            Icon(
                painter = painterResource(id = pieceOnSquare.drawable),
                contentDescription = "",
                tint = Color.Unspecified
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
        ChessBoard(board = board)
    }
}
