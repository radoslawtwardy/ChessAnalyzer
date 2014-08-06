package chessanalyzer.model.board

import chessanalyzer.model.figure.ChessFigure


/**
 * Describes single piece of chess board, where potentially may stay some figure [[chessanalyzer.model.figure.ChessFigure]]
 * @param index Index of piece on the chess board
 * @param actualFigure Figure which currently is staying on this piece. Of course also any figure may stay on this piece
 * @param previousFigures Figures which stayed on this piece in the past or is staying now
 */
case class ChessBoardPiece(index: ChessBoardIndex, actualFigure: Option[ChessFigure], previousFigures: Set[ChessFigure]) {

  /**
   * Check if input piece is threatened by current piece (figure staying on current piece).
   * This method not check existence of figure on input piece.
   * @param otherPiece Index of piece for which we checked
   * @return if figure staying on current piece threatening input piece of chessboard
   */
  def isThreateningPiece(otherPiece: ChessBoardIndex) = ???

}
