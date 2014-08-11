package chessanalyzer.model

import org.scalatest.{Matchers, FlatSpec}

import ChessFigure._

/**
* Tests suite to [[ChessBoard]]
* Checks if some settings figures on chess board is allowed (without threatening between any figures)
*/
class ChessBoardTest extends FlatSpec with Matchers {

  "Chess board" should "be not allowed, because some figure from board is threatening tested piece (chess board layout 1)" in {
    board1.boardAllowed(King, ChessBoardIndex(1, 2)) should be(false)
    board1.boardAllowed(King, ChessBoardIndex(2, 2)) should be(false)
    board1.boardAllowed(King, ChessBoardIndex(2, 3)) should be(false)
  }

  it should "be not allowed, because tested figure is threatening some figure on board  (chess board layout 1)" in {
    board1.boardAllowed(Knight, ChessBoardIndex(2, 1)) should be(false)
    board1.boardAllowed(Queen, ChessBoardIndex(3, 2)) should be(false)
  }

  it should "be allowed, because any figure from board is threatening tested piece and this piece is not threatening any figure from board (chess board layout 1)" in {
    board1.boardAllowed(Bishop, ChessBoardIndex(1, 1)) should be(true)
    board1.boardAllowed(Knight, ChessBoardIndex(3, 3)) should be(true)
  }

  "Chess board" should "be not allowed, because some figure from board is threatening tested piece (chess board layout 2)" in {
    board2.boardAllowed(King, ChessBoardIndex(1, 2)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(1, 3)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(1, 4)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(1, 5)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(2, 1)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(2, 2)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(2, 4)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(3, 1)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(3, 2)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(3, 3)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(3, 4)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(4, 1)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(4, 3)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(4, 4)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(4, 5)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(5, 1)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(5, 2)) should be (false)
    board2.boardAllowed(King, ChessBoardIndex(5, 3)) should be (false)
  }

  it should "be not allowed, because tested figure is threatening some figure on board  (chess board layout 2)" in {
    board2.boardAllowed(King, ChessBoardIndex(5, 4)) should be (false)
  }

  it should "be allowed, because any figure from board is threatening tested piece and this piece is not threatening any figure from board (chess board layout 2)" in {
    board2.boardAllowed(Bishop, ChessBoardIndex(2, 3)) should be (true)
    board2.boardAllowed(Knight, ChessBoardIndex(3, 5)) should be (true)
  }


  val pieces1 = Map(
    ChessBoardIndex(1, 3) -> King,
    ChessBoardIndex(3, 1) -> Knight
  )

  val pieces2 = Map(
    ChessBoardIndex(1, 1) -> Rook,
    ChessBoardIndex(2, 5) -> Bishop,
    ChessBoardIndex(4, 2) -> Queen,
    ChessBoardIndex(5, 5) -> Knight
  )

  val board1 = ChessBoard.create(3, 3, pieces1)
  val board2 = ChessBoard.create(5, 5, pieces2)
}
