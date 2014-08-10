package chessanalyzer.model.board

import org.scalatest.{Matchers, FlatSpec}
import chessanalyzer.model.figure.ChessFigure
import chessanalyzer.model.figure.ChessFigure._
import scala.Some

/**
 * Tests suite to [[chessanalyzer.model.board.ChessBoardPieceTest]]
 * Checks if some other pieces of chess board are threatening by chessanalyzer.figure which is staying on current piece
 */
class ChessBoardPieceTest extends FlatSpec with Matchers {

  "Chess board piece" should "threatens other piece if some chessanalyzer.figure standing in this piece and this chessanalyzer.figure threatens other piece" in {
    def piece(figure: ChessFigure) = ChessBoardPiece(ChessBoardIndex(3,3), Some(figure))

    piece(King).isThreateningPiece(ChessBoardIndex(4, 3)) should be (true)
    piece(Knight).isThreateningPiece(ChessBoardIndex(4, 5)) should be (true)
    piece(Bishop).isThreateningPiece(ChessBoardIndex(99, 99)) should be (true)
    piece(Rook).isThreateningPiece(ChessBoardIndex(3, 7)) should be (true)
    piece(Queen).isThreateningPiece(ChessBoardIndex(5, 1)) should be (true)
  }

  it should "not threatens other piece if some chessanalyzer.figure standing in this piece and this chessanalyzer.figure not threatens other piece" in {
    def piece(figure: ChessFigure) = ChessBoardPiece(ChessBoardIndex(3,3), Some(figure))

    piece(King).isThreateningPiece(ChessBoardIndex(1, 5)) should be (false)
    piece(Knight).isThreateningPiece(ChessBoardIndex(3, 2)) should be (false)
    piece(Bishop).isThreateningPiece(ChessBoardIndex(5, 3)) should be (false)
    piece(Rook).isThreateningPiece(ChessBoardIndex(5, 1)) should be (false)
    piece(Queen).isThreateningPiece(ChessBoardIndex(5, 4)) should be (false)
  }

  it should "not threatens other piece if any chessanalyzer.figure standing in this piece" in {
    val piece = ChessBoardPiece(ChessBoardIndex(3,3), None)

    piece.isThreateningPiece(ChessBoardIndex(4, 3)) should be (false)
    piece.isThreateningPiece(ChessBoardIndex(4, 5)) should be (false)
    piece.isThreateningPiece(ChessBoardIndex(99, 99)) should be (false)
    piece.isThreateningPiece(ChessBoardIndex(3, 7)) should be (false)
    piece.isThreateningPiece(ChessBoardIndex(5, 1)) should be (false)
  }

}
