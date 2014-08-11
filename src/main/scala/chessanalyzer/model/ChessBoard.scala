package chessanalyzer.model

import scala.collection.immutable.TreeMap
import scala.collection.SortedMap

/**
 * Describes chess board with M x N dimensions.
 * Chess board consist of < MxN non-empty pieces which are indexed by [[ChessBoardIndex]]
 * Chess board doesn't contain empty pieces (without figure)
 * @param size size of chess board (horizontal and vertical)
 * @param bondedPieces bonded (non-empty) pieces of board
 */
case class ChessBoard private(size: ChessBoardSize, bondedPieces: SortedMap[ChessBoardIndex, ChessFigure]) {

  require(size.m > 0 && size.n > 0, "the smallest size of chessboard is 1 x 1")
  require(bondedPieces.size <= size.m * size.n, "size of chessboard must be agreed with quantity of pieces")


  /**
   * Check if board is allowed.
   * It means that any figure on board not threatening figure standing on input index and input figure not threatening any figure on board.
   * @param figureToCheck Figure for which we checked
   * @param indexToCheck Index of checked figure
   * @return if board is allowed.
   */
  def boardAllowed(figureToCheck: ChessFigure, indexToCheck: ChessBoardIndex): Boolean = {
    bondedPieces.forall {
      case (index, figure) =>
          !figure.isThreatening(index, indexToCheck) && !figureToCheck.isThreatening(indexToCheck, index) // or if two pieces are not threatening itself then is ok
    }
  }

  /**
   * Build new chess board with updated piece
   * @param newFigure new figure on the board
   * @param index index of new figure
   * @return copy of this board with updated piece
   */
  def withUpdatedPiece(newFigure: ChessFigure, index: ChessBoardIndex) = {
    copy(bondedPieces = bondedPieces + (index -> newFigure))
  }

  /**
   * Build new chess board with cleared piece (in other words removing figure from piece)
   * @param index Index of piece which will be cleared
   * @return copy of this board with updated piece
   */
  def removeFigureFromPiece(index: ChessBoardIndex) = {
    copy(bondedPieces = bondedPieces - index)
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
  def empty(m: Int, n: Int): ChessBoard = apply(ChessBoardSize(m, n), SortedMap.empty[ChessBoardIndex, ChessFigure](new IndexOrdering(n)))

  /**
   * Create chess board
   * @param m horizontal size of chess board
   * @param n vertical size of chess board
   * @param bondedPieces figures and theirs indexes (could be unsorted)
   */
  def create(m: Int, n: Int, bondedPieces: Map[ChessBoardIndex, ChessFigure]): ChessBoard = {
    ChessBoard(ChessBoardSize(m, n), TreeMap(bondedPieces.toArray:_*)(new IndexOrdering(n)))
  }

  /**
   * Create set of possible chess board consisting of one figure
   * @param m horizontal size of chess board
   * @param n vertical size of chess board
   * @param figure figure on the chess board
   * @return set of possible chess board consisting of one figure
   */
  def create(m: Int, n: Int, figure: ChessFigure): Set[ChessBoard] = {
    val possibility = for {
      i <- 1 to m
      j <- 1 to n
    } yield ChessBoardIndex(i, j) -> figure

    possibility.map(setup => ChessBoard.create(m, n, Map(setup))).toSet
  }
}
