package chessanalyzer.figure

import org.scalatest.{Matchers, FlatSpec}
import chessanalyzer.model.figure.ChessFigure._
import chessanalyzer.model.board.ChessBoardIndex

/**
 * Tests suite to [[chessanalyzer.model.figure.ChessFigure]]
 * Checks possibility of existing some chessanalyzer.figure relative to the current without threatening
 */
class ChessFigureTest extends FlatSpec with Matchers {

  "King" should " threatens only adjacent figures" in {
    val position = ChessBoardIndex(3, 3)

    King.isThreatening(position, ChessBoardIndex(2, 2)) should be (true)
    King.isThreatening(position, ChessBoardIndex(2, 3)) should be (true)
    King.isThreatening(position, ChessBoardIndex(2, 4)) should be (true)
    King.isThreatening(position, ChessBoardIndex(3, 2)) should be (true)
    King.isThreatening(position, ChessBoardIndex(3, 4)) should be (true)
    King.isThreatening(position, ChessBoardIndex(4, 2)) should be (true)
    King.isThreatening(position, ChessBoardIndex(4, 3)) should be (true)
    King.isThreatening(position, ChessBoardIndex(4, 4)) should be (true)
    King.isThreatening(position, ChessBoardIndex(3, 3)) should be (true)
  }

  it should " not threatens chessanalyzer.figure which not adjacent"

  it should " not threatens figures not adjacent to it" in {
    val position = ChessBoardIndex(3, 3)

    King.isThreatening(position, ChessBoardIndex(1, 1)) should be (false)
    King.isThreatening(position, ChessBoardIndex(1, 2)) should be (false)
    King.isThreatening(position, ChessBoardIndex(1, 5)) should be (false)
    King.isThreatening(position, ChessBoardIndex(1, 3)) should be (false)
    King.isThreatening(position, ChessBoardIndex(5, 3)) should be (false)
    King.isThreatening(position, ChessBoardIndex(4, 1)) should be (false)
    King.isThreatening(position, ChessBoardIndex(99, 101)) should be (false)
  }

  "Knight" should " threatens only chessanalyzer.figure in distance 2+1 or 1+2" in {
    val position = ChessBoardIndex(3, 3)

    Knight.isThreatening(position, ChessBoardIndex(1, 2)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(1, 4)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(2, 1)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(2, 5)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(4, 1)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(4, 5)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(5, 2)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(5, 4)) should be (true)
    Knight.isThreatening(position, ChessBoardIndex(3, 3)) should be (true)
  }


  it should " not threatens chessanalyzer.figure which is have other distance then 2+1 or 1+2" in {
    val position = ChessBoardIndex(3, 3)

    Knight.isThreatening(position, ChessBoardIndex(1, 1)) should be (false)
    Knight.isThreatening(position, ChessBoardIndex(1, 5)) should be (false)
    Knight.isThreatening(position, ChessBoardIndex(2, 3)) should be (false)
    Knight.isThreatening(position, ChessBoardIndex(3, 2)) should be (false)
    Knight.isThreatening(position, ChessBoardIndex(3, 4)) should be (false)
    Knight.isThreatening(position, ChessBoardIndex(4, 3)) should be (false)
    Knight.isThreatening(position, ChessBoardIndex(5, 3)) should be (false)
    Knight.isThreatening(position, ChessBoardIndex(99, 101)) should be (false)
  }

  "Bishop" should " threatens only chessanalyzer.figure which stay diagonally to it" in {
    val position = ChessBoardIndex(3, 3)

    Bishop.isThreatening(position, ChessBoardIndex(1, 1)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(2, 2)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(4, 4)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(5, 5)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(1, 5)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(2, 4)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(4, 2)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(5, 1)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(99, 99)) should be (true)
    Bishop.isThreatening(position, ChessBoardIndex(3, 3)) should be (true)
  }

  it should " not threatens chessanalyzer.figure which isn't stay diagonally to it" in {
    val position = ChessBoardIndex(3, 3)

    Bishop.isThreatening(position, ChessBoardIndex(4, 3)) should be (false)
    Bishop.isThreatening(position, ChessBoardIndex(3, 5)) should be (false)
    Bishop.isThreatening(position, ChessBoardIndex(4, 3)) should be (false)
    Bishop.isThreatening(position, ChessBoardIndex(3, 2)) should be (false)
    Bishop.isThreatening(position, ChessBoardIndex(5, 3)) should be (false)
    Bishop.isThreatening(position, ChessBoardIndex(99, 100)) should be (false)
  }

  "Rook" should " threatens chessanalyzer.figure which stay opposite to it" in {
    val position = ChessBoardIndex(3, 4)

    Rook.isThreatening(position, ChessBoardIndex(1, 4)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(2, 4)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(4, 4)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(5, 4)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(3, 1)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(3, 2)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(3, 3)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(3, 5)) should be (true)
    Rook.isThreatening(position, ChessBoardIndex(3, 101)) should be (true)
  }

  it should " not threatens chessanalyzer.figure which not stay opposite to it" in {
    val position = ChessBoardIndex(3, 4)

    Rook.isThreatening(position, ChessBoardIndex(1, 1)) should be (false)
    Rook.isThreatening(position, ChessBoardIndex(2, 2)) should be (false)
    Rook.isThreatening(position, ChessBoardIndex(2, 3)) should be (false)
    Rook.isThreatening(position, ChessBoardIndex(4, 3)) should be (false)
    Rook.isThreatening(position, ChessBoardIndex(4, 3)) should be (false)
    Rook.isThreatening(position, ChessBoardIndex(4, 5)) should be (false)
    Rook.isThreatening(position, ChessBoardIndex(5, 99)) should be (false)
  }

  "Queen" should " threatens chessanalyzer.figure which stay opposite to it or diagonally to it" in {
    val position = ChessBoardIndex(3, 3)

    Queen.isThreatening(position, ChessBoardIndex(1, 1)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(2, 2)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(4, 4)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(5, 5)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(1, 5)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(2, 4)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(4, 2)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(5, 1)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(99, 99)) should be (true)

    Queen.isThreatening(position, ChessBoardIndex(1, 3)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(2, 3)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(4, 3)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(5, 3)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(3, 1)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(3, 2)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(3, 4)) should be (true)
    Queen.isThreatening(position, ChessBoardIndex(3, 5)) should be (true)
  }

  it should " not threatens chessanalyzer.figure which not stay opposite to it and not stay diagonally to it" in {
    val position = ChessBoardIndex(3, 3)

    Queen.isThreatening(position, ChessBoardIndex(1, 2)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(1, 4)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(2, 1)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(2, 5)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(4, 1)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(4, 5)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(5, 2)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(5, 4)) should be (false)
    Queen.isThreatening(position, ChessBoardIndex(99, 100)) should be (false)
  }
}
