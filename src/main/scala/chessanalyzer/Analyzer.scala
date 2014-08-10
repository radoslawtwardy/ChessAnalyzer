package chessanalyzer

import chessanalyzer.model.figure.ChessFigure
import chessanalyzer.model.board.{ChessBoardIndex, ChessBoardPiece, ChessBoard}
import com.typesafe.scalalogging.LazyLogging
import chessanalyzer.model.figure.ChessFigure._
import chessanalyzer.model.board.ChessBoardIndex
import chessanalyzer.model.board.ChessBoardPiece
import scala.Some
import scala.annotation.tailrec

/**
 * Tool responsible for some chess analyzing methods
 */
object Analyzer extends LazyLogging {
  /**
   * Calculate possible figure setups on the chess board.
   * Possible means that any figure is not threatening any of the other figures from the chess board.
   * @param m horizontal size of analyzed chess boards.
   * @param n vertical size of analyzed chess boards.
   * @param figures figures which must standing on the chess boards. Order of this figures does not matter.
   * @return all chess on size m x n where standing all of input figures and aby figure is not threatening any of the other figures from the chess board.
   */
  def possibleSetups(m: Int, n: Int, figures: List[ChessFigure]): Set[ChessBoard] = {
    logger.info("Start analyze " + m + "x" + n + " chess board with figures: " + figures.mkString(", "))
    val result = findPossibleSetups(ChessBoard.empty(m, n), figures, List.empty)
    logger.info("Total allowed setups is: " + result.size)
    result
  }

  @tailrec
  private def findPossibleSetups(board: ChessBoard, figures: List[ChessFigure], previousPieces: List[ChessBoardPiece], acc: Set[ChessBoard] = Set.empty, startPosition: ChessBoardIndex = ChessBoardIndex(1,1)): Set[ChessBoard] = {

    lazy val previousPiece = previousPieces.headOption.getOrElse(sys.error("Implementation error: previous peace always must exist if no allowed field was found"))
    lazy val previousFigure = previousPiece.actualFigure.getOrElse(sys.error("Implementation error: previous piece must have a figure"))

    figures.headOption match {
      case Some(figure) =>
        //searching a position where new figure can be standing
        val allowedPosition = board.pieces.from(startPosition).collectFirst {
          case (ind, _) if board.boardAllowed(ChessBoardPiece(ind, Some(figure))) => ind
        }

        allowedPosition match {
          case Some(ind) =>
            val newPiece = ChessBoardPiece(ind, Some(figure))
            //if allowed position exist then update chess board on try stands next figure
            findPossibleSetups(board.withUpdatedPiece(newPiece), figures.tail, newPiece :: previousPieces, acc)
          case None =>
            //if allowed position not exist then clear last putted figure on chess board and move it (start from next position)
            if(previousPieces.isEmpty) acc //pieces not exist means out of the range of board
            else findPossibleSetups(board.removeFigureFromPiece(previousPiece.index), previousFigure :: figures, previousPieces.tail, acc, previousPiece.index.nextIndex(board.n))
        }
      case None => //if all figure was used then return board and find next possible
          findPossibleSetups(board.removeFigureFromPiece(previousPiece.index), List(previousFigure), previousPieces.tail, acc + board, previousPiece.index.nextIndex(board.n))
    }

  }
}
