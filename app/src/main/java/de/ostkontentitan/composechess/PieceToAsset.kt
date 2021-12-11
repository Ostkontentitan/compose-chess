package de.ostkontentitan.composechess

import de.ostkontentitan.composechess.core.Bishop
import de.ostkontentitan.composechess.core.King
import de.ostkontentitan.composechess.core.Knight
import de.ostkontentitan.composechess.core.Pawn
import de.ostkontentitan.composechess.core.Piece
import de.ostkontentitan.composechess.core.Queen
import de.ostkontentitan.composechess.core.Rook

val Piece.drawable
    get() = when (this) {
        is Bishop -> if (isWhite()) R.drawable.ic_wb else R.drawable.ic_bb
        is King -> if (isWhite()) R.drawable.ic_wk else R.drawable.ic_bk
        is Knight -> if (isWhite()) R.drawable.ic_wn else R.drawable.ic_bn
        is Pawn -> if (isWhite()) R.drawable.ic_wp else R.drawable.ic_bp
        is Queen -> if (isWhite()) R.drawable.ic_wq else R.drawable.ic_bq
        is Rook -> if (isWhite()) R.drawable.ic_wr else R.drawable.ic_br
    }
