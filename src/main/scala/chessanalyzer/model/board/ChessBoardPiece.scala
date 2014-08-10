package chessanalyzer.model.board

import chessanalyzer.model.figure.ChessFigure


/**
 * Describes single piece of chess board, where potentially may stay some chessanalyzer.figure [[chessanalyzer.model.figure.ChessFigure]]
 * @param index Index of piece on the chess board
 * @param actualFigure Figure which currently is staying on this piece. Of course also any chessanalyzer.figure may stay on this piece
 */
case class ChessBoardPiece(index: ChessBoardIndex, actualFigure: Option[ChessFigure]) {

  /**
   * Check if input piece is threatened by current piece (chessanalyzer.figure staying on current piece).
   * This method not check existence of chessanalyzer.figure on input piece.
   * @param otherPiece Index of piece for which we checked
   * @return if chessanalyzer.figure staying on current piece threatening input piece of chessboard
   */
  def isThreateningPiece(otherPiece: ChessBoardIndex): Boolean = {
    actualFigure.map(_.isThreatening(index, otherPiece)).getOrElse(false)
  }

}
