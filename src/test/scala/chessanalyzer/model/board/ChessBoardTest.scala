package chessanalyzer.model.board

import org.scalatest.{Matchers, FlatSpec}

import chessanalyzer.model.figure.ChessFigure._
/**
* Tests suite to [[chessanalyzer.model.board.ChessBoard]]
* Checks if some settings figures on chess board is allowed (without threatening between any figures)
*/
class ChessBoardTest extends FlatSpec with Matchers {

  "Chess board " should "threatens some piece (chess board layout 1)" in {
    board2.isThreateningPiece(ChessBoardIndex(1, 1)) should be(true)
    board2.isThreateningPiece(ChessBoardIndex(2, 1)) should be(true)
    board2.isThreateningPiece(ChessBoardIndex(3, 2)) should be(true)
    board2.isThreateningPiece(ChessBoardIndex(3, 3)) should be(true)
  }

  it should "not threatens some piece (chess board layout 1)" in {
    board2.isThreateningPiece(ChessBoardIndex(1, 2)) should be(false)
    board2.isThreateningPiece(ChessBoardIndex(2, 2)) should be(false)
    board2.isThreateningPiece(ChessBoardIndex(2, 3)) should be(false)
  }

  it should "threatens some piece (chess board layout 2)" in {
    board2.isThreateningPiece(ChessBoardIndex(1, 2)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(1, 3)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(1, 4)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(1, 5)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(2, 1)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(2, 2)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(2, 4)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(3, 1)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(3, 2)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(3, 3)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(3, 4)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(4, 1)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(4, 3)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(4, 4)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(4, 5)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(5, 1)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(5, 2)) should be (true)
    board2.isThreateningPiece(ChessBoardIndex(5, 3)) should be (true)  
  }

  it should "not threatens some piece (chess board layout 2)" in {
    board2.isThreateningPiece(ChessBoardIndex(2, 3)) should be (false)
    board2.isThreateningPiece(ChessBoardIndex(3, 5)) should be (false)
    board2.isThreateningPiece(ChessBoardIndex(5, 4)) should be (false)
  }

  val pieces1 = Map(
    ChessBoardIndex(1, 1) -> ChessBoardPiece(ChessBoardIndex(1, 1), None, Set.empty),
    ChessBoardIndex(1, 2) -> ChessBoardPiece(ChessBoardIndex(1, 2), None, Set.empty),
    ChessBoardIndex(1, 3) -> ChessBoardPiece(ChessBoardIndex(1, 3), Some(King), Set.empty),
    ChessBoardIndex(2, 1) -> ChessBoardPiece(ChessBoardIndex(2, 1), None, Set.empty),
    ChessBoardIndex(2, 2) -> ChessBoardPiece(ChessBoardIndex(2, 2), None, Set.empty),
    ChessBoardIndex(2, 3) -> ChessBoardPiece(ChessBoardIndex(2, 3), None, Set.empty),
    ChessBoardIndex(3, 1) -> ChessBoardPiece(ChessBoardIndex(3, 1), Some(Knight), Set.empty),
    ChessBoardIndex(3, 2) -> ChessBoardPiece(ChessBoardIndex(3, 2), None, Set.empty),
    ChessBoardIndex(3, 3) -> ChessBoardPiece(ChessBoardIndex(3, 3), None, Set.empty)
  )

  val pieces2 = Map(
    ChessBoardIndex(1, 1) -> ChessBoardPiece(ChessBoardIndex(1, 1), Some(Rook), Set.empty),
    ChessBoardIndex(1, 2) -> ChessBoardPiece(ChessBoardIndex(1, 2), None, Set.empty),
    ChessBoardIndex(1, 3) -> ChessBoardPiece(ChessBoardIndex(1, 3), None, Set.empty),
    ChessBoardIndex(1, 4) -> ChessBoardPiece(ChessBoardIndex(1, 4), None, Set.empty),
    ChessBoardIndex(1, 5) -> ChessBoardPiece(ChessBoardIndex(1, 5), None, Set.empty),
    ChessBoardIndex(2, 1) -> ChessBoardPiece(ChessBoardIndex(2, 1), None, Set.empty),
    ChessBoardIndex(2, 2) -> ChessBoardPiece(ChessBoardIndex(2, 2), None, Set.empty),
    ChessBoardIndex(2, 3) -> ChessBoardPiece(ChessBoardIndex(2, 3), None, Set.empty),
    ChessBoardIndex(2, 4) -> ChessBoardPiece(ChessBoardIndex(2, 4), None, Set.empty),
    ChessBoardIndex(2, 5) -> ChessBoardPiece(ChessBoardIndex(2, 5), Some(Bishop), Set.empty),
    ChessBoardIndex(3, 1) -> ChessBoardPiece(ChessBoardIndex(3, 1), None, Set.empty),
    ChessBoardIndex(3, 2) -> ChessBoardPiece(ChessBoardIndex(3, 2), None, Set.empty),
    ChessBoardIndex(3, 3) -> ChessBoardPiece(ChessBoardIndex(3, 3), None, Set.empty),
    ChessBoardIndex(3, 4) -> ChessBoardPiece(ChessBoardIndex(3, 4), None, Set.empty),
    ChessBoardIndex(3, 5) -> ChessBoardPiece(ChessBoardIndex(3, 5), None, Set.empty),
    ChessBoardIndex(4, 1) -> ChessBoardPiece(ChessBoardIndex(4, 1), None, Set.empty),
    ChessBoardIndex(4, 2) -> ChessBoardPiece(ChessBoardIndex(4, 2), Some(Queen), Set.empty),
    ChessBoardIndex(4, 3) -> ChessBoardPiece(ChessBoardIndex(4, 3), None, Set.empty),
    ChessBoardIndex(4, 4) -> ChessBoardPiece(ChessBoardIndex(4, 4), None, Set.empty),
    ChessBoardIndex(4, 5) -> ChessBoardPiece(ChessBoardIndex(4, 5), None, Set.empty),
    ChessBoardIndex(5, 1) -> ChessBoardPiece(ChessBoardIndex(5, 1), None, Set.empty),
    ChessBoardIndex(5, 2) -> ChessBoardPiece(ChessBoardIndex(5, 2), None, Set.empty),
    ChessBoardIndex(5, 3) -> ChessBoardPiece(ChessBoardIndex(5, 3), None, Set.empty),
    ChessBoardIndex(5, 4) -> ChessBoardPiece(ChessBoardIndex(5, 4), None, Set.empty),
    ChessBoardIndex(5, 5) -> ChessBoardPiece(ChessBoardIndex(5, 5), Some(Knight), Set.empty)
  )

  val board1 = ChessBoard(3, 3, pieces1)
  val board2 = ChessBoard(5, 5, pieces2)
}
