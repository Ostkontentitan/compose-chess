package de.ostkontentitan.composechess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.ostkontentitan.composechess.core.Board
import de.ostkontentitan.composechess.core.setupForNewGame
import de.ostkontentitan.composechess.ui.theme.ComposeChessTheme

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
    Text(modifier = Modifier.fillMaxWidth(), text = board.stringRep)
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
