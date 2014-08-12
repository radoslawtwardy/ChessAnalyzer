package chessanalyzer

import chessanalyzer.model.{ChessBoard, ChessBoardIndex, ChessFigure}
import scala.annotation.tailrec
import scala.Some
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
 * Tool responsible for some chess analyzing methods
 */
object Analyzer {

  /**
   * Calculate possible figure setups on the chess board sequential method
   * Possible means that any figure is not threatening any of the other figures from the chess board.
   * @param m horizontal size of analyzed chess boards.
   * @param n vertical size of analyzed chess boards.
   * @param figures figures which must standing on the chess boards. Order of this figures does not matter.
   * @return all chess on size m x n where standing all of input figures and aby figure is not threatening any of the other figures from the chess board.
   */
  def possibleSetupsSequential(m: Int, n: Int, figures: List[ChessFigure]): Set[ChessBoard] = {
    if(figures.isEmpty) Set.empty[ChessBoard] //no figure - no setups
    else findPossibleSetups(ChessBoard.empty(m, n), figures, List.empty)
  }

  /**
   * Calculate possible figure setups on the chess board - parallel version
   * Possible means that any figure is not threatening any of the other figures from the chess board.
   * @param m horizontal size of analyzed chess boards.
   * @param n vertical size of analyzed chess boards.
   * @param figures figures which must standing on the chess boards. Order of this figures does not matter.
   * @return all chess on size m x n where standing all of input figures and aby figure is not threatening any of the other figures from the chess board.
   */
  def possibleSetups(m: Int, n: Int, figures: List[ChessFigure]): Set[ChessBoard] = {

    lazy val startBoards = ChessBoard.create(m, n, figures.head)

    val results = if(figures.isEmpty) {
      //no figure - no setups
      Set(Future(Set.empty[ChessBoard]))
    } else if(figures.size == 1) {
      //if recursively we working on pre-populated board (1 figure) then one figure is our edge case - we can't run recursively alg for empty board
      Set(Future(startBoards))
    } else {
      //in other way we run main algorithm - pieces of solution will be wrapped in to future
      val startFigures = figures.tail
      startBoards.map {
        board =>
          Future(findPossibleSetups(board, startFigures, List.empty))
      }
    }

    Await.result(Future.sequence(results), Duration.Inf).flatten
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
