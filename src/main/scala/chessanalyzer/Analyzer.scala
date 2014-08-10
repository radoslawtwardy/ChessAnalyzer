package chessanalyzer

import chessanalyzer.model.figure.ChessFigure
import chessanalyzer.model.board.ChessBoard

/**
 * Tool responsible for some chess analyzing methods
 */
object Analyzer {

  /**
   * Calculate possible chessanalyzer.figure setups on the chess board.
   * Possible means that any chessanalyzer.figure is not threatening any of the other figures from the chess board.
   * @param m horizontal size of analyzed chess boards.
   * @param n vertical size of analyzed chess boards.
   * @param figures figures which must standing on the chess boards. Order of this figures does not matter.
   * @return all chess on size m x n where standing all of input figures and aby chessanalyzer.figure is not threatening any of the other figures from the chess board.
   */
  def possibleSetups(m: Int, n: Int, figures: Seq[ChessFigure]): Set[ChessBoard] = ???
}
