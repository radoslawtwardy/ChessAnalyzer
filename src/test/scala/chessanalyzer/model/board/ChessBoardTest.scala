package chessanalyzer.model.board

import org.scalatest.{Matchers, FlatSpec}

import chessanalyzer.model.figure.ChessFigure._
/**
* Tests suite to [[chessanalyzer.model.board.ChessBoard]]
* Checks if some settings figures on chess board is allowed (without threatening between any figures)
*/
class ChessBoardTest extends FlatSpec with Matchers {

  "Chess board" should "be not allowed, because some figure from board is threatening tested piece (chess board layout 1)" in {
    board1.boardAllowed(ChessBoardPiece(ChessBoardIndex(1, 2), Some(King))) should be(false)
    board1.boardAllowed(ChessBoardPiece(ChessBoardIndex(2, 2), Some(King))) should be(false)
    board1.boardAllowed(ChessBoardPiece(ChessBoardIndex(2, 3), Some(King))) should be(false)
  }

  it should "be not allowed, because tested figure is threatening some figure on board  (chess board layout 1)" in {
    board1.boardAllowed(ChessBoardPiece(ChessBoardIndex(2, 1), Some(Knight))) should be(false)
    board1.boardAllowed(ChessBoardPiece(ChessBoardIndex(3, 2), Some(Queen))) should be(false)
  }

  it should "be allowed, because any figure from board is threatening tested piece and this piece is not threatening any figure from board (chess board layout 1)" in {
    board1.boardAllowed(ChessBoardPiece(ChessBoardIndex(1, 1), Some(Bishop))) should be(true)
    board1.boardAllowed(ChessBoardPiece(ChessBoardIndex(3, 3), Some(Knight))) should be(true)
  }

  "Chess board" should "be not allowed, because some figure from board is threatening tested piece (chess board layout 2)" in {
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(1, 2), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(1, 3), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(1, 4), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(1, 5), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(2, 1), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(2, 2), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(2, 4), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(3, 1), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(3, 2), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(3, 3), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(3, 4), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(4, 1), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(4, 3), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(4, 4), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(4, 5), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(5, 1), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(5, 2), Some(King))) should be (false)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(5, 3), Some(King))) should be (false)
  }

  it should "be not allowed, because tested figure is threatening some figure on board  (chess board layout 2)" in {
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(5, 4), Some(King))) should be (false)
  }

  it should "be allowed, because any figure from board is threatening tested piece and this piece is not threatening any figure from board (chess board layout 2)" in {
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(2, 3), Some(Bishop))) should be (true)
    board2.boardAllowed(ChessBoardPiece(ChessBoardIndex(3, 5), Some(Knight))) should be (true)
  }


  val pieces1 = Map(
    ChessBoardIndex(1, 1) -> ChessBoardPiece(ChessBoardIndex(1, 1), None),
    ChessBoardIndex(1, 2) -> ChessBoardPiece(ChessBoardIndex(1, 2), None),
    ChessBoardIndex(1, 3) -> ChessBoardPiece(ChessBoardIndex(1, 3), Some(King)),
    ChessBoardIndex(2, 1) -> ChessBoardPiece(ChessBoardIndex(2, 1), None),
    ChessBoardIndex(2, 2) -> ChessBoardPiece(ChessBoardIndex(2, 2), None),
    ChessBoardIndex(2, 3) -> ChessBoardPiece(ChessBoardIndex(2, 3), None),
    ChessBoardIndex(3, 1) -> ChessBoardPiece(ChessBoardIndex(3, 1), Some(Knight)),
    ChessBoardIndex(3, 2) -> ChessBoardPiece(ChessBoardIndex(3, 2), None),
    ChessBoardIndex(3, 3) -> ChessBoardPiece(ChessBoardIndex(3, 3), None)
  )

  val pieces2 = Map(
    ChessBoardIndex(1, 1) -> ChessBoardPiece(ChessBoardIndex(1, 1), Some(Rook)),
    ChessBoardIndex(1, 2) -> ChessBoardPiece(ChessBoardIndex(1, 2), None),
    ChessBoardIndex(1, 3) -> ChessBoardPiece(ChessBoardIndex(1, 3), None),
    ChessBoardIndex(1, 4) -> ChessBoardPiece(ChessBoardIndex(1, 4), None),
    ChessBoardIndex(1, 5) -> ChessBoardPiece(ChessBoardIndex(1, 5), None),
    ChessBoardIndex(2, 1) -> ChessBoardPiece(ChessBoardIndex(2, 1), None),
    ChessBoardIndex(2, 2) -> ChessBoardPiece(ChessBoardIndex(2, 2), None),
    ChessBoardIndex(2, 3) -> ChessBoardPiece(ChessBoardIndex(2, 3), None),
    ChessBoardIndex(2, 4) -> ChessBoardPiece(ChessBoardIndex(2, 4), None),
    ChessBoardIndex(2, 5) -> ChessBoardPiece(ChessBoardIndex(2, 5), Some(Bishop)),
    ChessBoardIndex(3, 1) -> ChessBoardPiece(ChessBoardIndex(3, 1), None),
    ChessBoardIndex(3, 2) -> ChessBoardPiece(ChessBoardIndex(3, 2), None),
    ChessBoardIndex(3, 3) -> ChessBoardPiece(ChessBoardIndex(3, 3), None),
    ChessBoardIndex(3, 4) -> ChessBoardPiece(ChessBoardIndex(3, 4), None),
    ChessBoardIndex(3, 5) -> ChessBoardPiece(ChessBoardIndex(3, 5), None),
    ChessBoardIndex(4, 1) -> ChessBoardPiece(ChessBoardIndex(4, 1), None),
    ChessBoardIndex(4, 2) -> ChessBoardPiece(ChessBoardIndex(4, 2), Some(Queen)),
    ChessBoardIndex(4, 3) -> ChessBoardPiece(ChessBoardIndex(4, 3), None),
    ChessBoardIndex(4, 4) -> ChessBoardPiece(ChessBoardIndex(4, 4), None),
    ChessBoardIndex(4, 5) -> ChessBoardPiece(ChessBoardIndex(4, 5), None),
    ChessBoardIndex(5, 1) -> ChessBoardPiece(ChessBoardIndex(5, 1), None),
    ChessBoardIndex(5, 2) -> ChessBoardPiece(ChessBoardIndex(5, 2), None),
    ChessBoardIndex(5, 3) -> ChessBoardPiece(ChessBoardIndex(5, 3), None),
    ChessBoardIndex(5, 4) -> ChessBoardPiece(ChessBoardIndex(5, 4), None),
    ChessBoardIndex(5, 5) -> ChessBoardPiece(ChessBoardIndex(5, 5), Some(Knight))
  )

  val board1 = ChessBoard(3, 3, pieces1)
  val board2 = ChessBoard(5, 5, pieces2)
}
