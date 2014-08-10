package chessanalyzer.model.board

import scala.collection.SortedMap
import scala.collection.immutable.TreeMap

/**
 * Describes chess board with M x N dimensions.
 * Chess board consist of MxN pieces [[ChessBoardPiece]] which are indexed by [[ChessBoardIndex]]
 * @param m horizontal size of chess board
 * @param n vertical size of chess board
 * @param pieces all (indexed) pieces of board
 */
case class ChessBoard(m: Int, n: Int, pieces: SortedMap[ChessBoardIndex, ChessBoardPiece]) {

  require(m > 0 && n > 0, "the smallest size of chessboard is 1 x 1")
  require(pieces.size == m * n, "size of chessboard must be agreed with quantity of pieces")

  /**
   * Check if board is allowed.
   * It means that any figure on board not threatening figure standing on input index and input figure not threatening any figure on board.
   * @param pieceToCheck Piece for which we checked
   * @return if board is allowed.
   */
  def boardAllowed(pieceToCheck: ChessBoardPiece): Boolean = {
    pieces.forall {
      case (index, piece) =>
         piece.actualFigure.isEmpty || pieceToCheck.actualFigure.isEmpty || // if on of compared pieces is empty then is ok
          !piece.isThreateningPiece(pieceToCheck.index) && !pieceToCheck.isThreateningPiece(index) // or if two pieces are not threatening itself then is ok
    }
  }

  /**
   * Build new chess board with updated piece
   * @param piece new piece on the board
   * @return copy of this board with updated piece
   */
  def withUpdatedPiece(piece: ChessBoardPiece) = {
    copy(pieces = pieces + (piece.index -> piece))
  }

  /**
   * Build new chess board with cleared piece (in other words removing figure from piece)
   * @param index Index of piece which will be cleared
   * @return copy of this board with updated piece
   */
  def removeFigureFromPiece(index: ChessBoardIndex) = {
    withUpdatedPiece(ChessBoardPiece(index, None))
  }
}

object ChessBoard {

  class IndexOrdering(n: Int) extends Ordering[ChessBoardIndex] {
    implicit def index(ind: ChessBoardIndex) = ind.i*n + ind.j
    override def compare(x: ChessBoardIndex, y: ChessBoardIndex): Int = x - y
  }

  /**
   * Create empty chess board
   * @param m horizontal size of chess board
   * @param n vertical size of chess board
   * @return empty chess board
   */
  def apply(m: Int, n: Int): ChessBoard = {
    val indexes = for {
      i <- 1 to m
      j <- 1 to n
    } yield ChessBoardIndex(i, j) -> ChessBoardPiece(ChessBoardIndex(i, j), None)

    ChessBoard(m, n, TreeMap(indexes:_*)(new IndexOrdering(n)))
  }

  /**
  * Create empty chess board
  * @param m horizontal size of chess board
  * @param n vertical size of chess board
  * @return empty chess board
  */
  def empty(m: Int, n: Int): ChessBoard = apply(m, n)
}
