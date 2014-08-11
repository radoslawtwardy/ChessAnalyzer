package chessanalyzer

import com.typesafe.scalalogging.LazyLogging
import chessanalyzer.model.{ChessBoard, ChessBoardIndex, ChessFigure}
import ChessFigure._
import scala.annotation.tailrec
import java.util.Date
import scala.Some

/**
 * Tool responsible for some chess analyzing methods
 */
object Analyzer extends App with LazyLogging {

  possibleSetups(7, 7, List(Queen, Queen, Bishop, Bishop, King, King, Knight))

  /**
   * Calculate possible figure setups on the chess board.
   * Possible means that any figure is not threatening any of the other figures from the chess board.
   * @param m horizontal size of analyzed chess boards.
   * @param n vertical size of analyzed chess boards.
   * @param figures figures which must standing on the chess boards. Order of this figures does not matter.
   * @return all chess on size m x n where standing all of input figures and aby figure is not threatening any of the other figures from the chess board.
   */
  def possibleSetups(m: Int, n: Int, figures: List[ChessFigure]): Set[ChessBoard] = {
    val st = new Date()
    logger.info("Start analyze " + m + "x" + n + " chess board with figures: " + figures.mkString(", "))
    val result = findPossibleSetups(ChessBoard.empty(m, n), figures, List.empty)
    logger.info("Total allowed setups is: " + result.size)
    val e = new Date()
    val r = (e.getTime - st.getTime)/1000
    logger.info("Total time: " + r + " s")
    result
  }

  @tailrec
  private def findPossibleSetups(board: ChessBoard, figures: List[ChessFigure], previousPieces: List[(ChessBoardIndex, ChessFigure)], acc: Set[ChessBoard] = Set.empty, actualPosition: Option[ChessBoardIndex] = Some(ChessBoardIndex(1,1))): Set[ChessBoard] = {

    implicit def boardSize = board.size

    //true only if board is allowed but is not full (exists some free figures)
    val notFullBoardAllowed = (actualPosition, figures.headOption) match {
      case (Some(pos), Some(fig)) => board.boardAllowed(fig, pos)
      case _ => false
    }

    (figures.headOption, previousPieces.headOption, notFullBoardAllowed, actualPosition) match {
      case (None, Some((prevIndex, prevFigure)),_ , _) =>
        //if all figure was used then return board and find next possible
        findPossibleSetups(board.removeFigureFromPiece(prevIndex), List(prevFigure), previousPieces.tail, acc + board, prevIndex.nextIndex)

      case (Some(firstFigure), _, true, Some(actualPos)) =>
        //if figure is allowed on actual position then update chess board on try stands next figure
        findPossibleSetups(board.withUpdatedPiece(firstFigure, actualPos), figures.tail, (actualPos, firstFigure) :: previousPieces, acc)

      case (_, Some((prevIndex, prevFigure)), false, None) =>
        //if figure is not allowed and position not exist then clear last putted figure on chess board and move it (start from next position)
        findPossibleSetups(board.removeFigureFromPiece(prevIndex), prevFigure :: figures, previousPieces.tail, acc, prevIndex.nextIndex)

      case (_, _, false, Some(actualPos)) =>
        //if figure is not allowed on actual position then try next position
        findPossibleSetups(board, figures, previousPieces, acc, actualPos.nextIndex)

      case (_, None, false, None) =>
        //if actual index not exist and is no previous figure (all figure in hand) then alogirtm must end
        acc

      case (fig, prev, allowed, pos) =>
        sys.error("Implementation ERROR: invalid chess board.\n" + "fig: " + fig + "\n"+ "prev: " + prev + "\n"+ "allowed: " + allowed + "\n"+ "pos: " + pos + "\n")
   }

  }
}
